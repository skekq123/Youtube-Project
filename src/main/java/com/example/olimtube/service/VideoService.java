package com.example.olimtube.service;

import com.example.olimtube.component.S3toColudFront;
import com.example.olimtube.model.Category;
import com.example.olimtube.model.User;
import com.example.olimtube.model.Video;
import com.example.olimtube.repository.UserRepository;
import com.example.olimtube.repository.VideoRepository;
import com.example.olimtube.requestDto.VideoRequestDto;
import com.example.olimtube.responseDto.SameCategoryVideoResponseDto;
import com.example.olimtube.responseDto.VideoDetailResponeDto;
import com.example.olimtube.responseDto.VideoResponseDto;
import com.example.olimtube.responseDto.VideoUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoryService categoryService;
    private final UserRepository userRepository;
    private final S3toColudFront s3toColudFront;

    @Transactional
    public List<VideoResponseDto> getVideosInfo() {
        List<VideoResponseDto> videoResponseDtos = new ArrayList<>();

        List<Video> videos = videoRepository.findAllByOrderByCreatedAtDesc();

        for(Video curVideo : videos) {
            Long curUserId = curVideo.getUser().getId();
            User curUser = userRepository.getById(curUserId);

            VideoUserResponseDto videoUserResponseDto = new VideoUserResponseDto(curUser);
            VideoResponseDto curDto = new VideoResponseDto(curVideo, videoUserResponseDto);
            videoResponseDtos.add(curDto);
        }

        return videoResponseDtos;
    }

    @Transactional
    public void uploadVideo(VideoRequestDto videoRequestDto, String imgUrl, User user, String videoUrl) {
        Category category = categoryService.createCategory(videoRequestDto.getCategory());

        imgUrl = s3toColudFront.changeUrl(imgUrl);
        videoUrl = s3toColudFront.changeUrl(videoUrl);

        Video video = new Video(videoRequestDto, imgUrl, user, category, videoUrl);

        videoRepository.save(video);
    }

    @Transactional
    public VideoDetailResponeDto detailVideo(Long video_id) {

        Video video = videoRepository.getById(video_id);
        video.setViews(video.getViews() + 1);

        Category category = video.getCategory();

        VideoUserResponseDto videoUserResponseDto = new VideoUserResponseDto(video.getUser());

        List<SameCategoryVideoResponseDto> sameCategoryVideoResponseDtos = new ArrayList<>();

        List<Category> categories = categoryService.getCategories(category.getCategoryNumber());

        List<Video> videos = new ArrayList<>();

        for(Category curCategroy: categories) {
            List<Video> curVideos = curCategroy.getVideos();
            for(Video curVideo : curVideos) {
                videos.add(curVideo);
            }
        }

        for(Video curVideo : videos) {
            SameCategoryVideoResponseDto sameCategoryVideoResponseDto = new SameCategoryVideoResponseDto(curVideo);
            if(sameCategoryVideoResponseDto.getVideo_id() == video_id) continue;

            sameCategoryVideoResponseDtos.add(sameCategoryVideoResponseDto);
        }

        VideoDetailResponeDto videoDetailResponeDto = new VideoDetailResponeDto(videoUserResponseDto, video, category, sameCategoryVideoResponseDtos);
        return videoDetailResponeDto;
    }

    public ResponseEntity deleteVideo(Long video_id, User user) {
        Video video = videoRepository.getById(video_id);
        if(video.getUser().getId() != user.getId()) {
            return ResponseEntity.badRequest().body("?????? ???????????? ?????? ???????????????.");
        }

        videoRepository.delete(video);
        //ToDo: s3????????? ????????????

        return ResponseEntity.ok().body("????????????");
    }
}
