package com.example.olimtube.controller;

import com.example.olimtube.component.S3Uploader;
import com.example.olimtube.model.User;
import com.example.olimtube.model.Video;
import com.example.olimtube.requestDto.VideoRequestDto;
import com.example.olimtube.responseDto.VideoDetailResponeDto;
import com.example.olimtube.responseDto.VideoResponseDto;
import com.example.olimtube.security.UserDetailsImpl;
import com.example.olimtube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    public ResponseEntity uploadVideo(@RequestPart(value = "contents")VideoRequestDto videoRequestDto,
                                      @RequestPart(value = "img", required = false) MultipartFile multipartFile,
                                      @RequestPart(value = "video", required = false) MultipartFile multipartVideo,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String imgUrl = "";
        if(!multipartFile.isEmpty()) imgUrl = s3Uploader.upload(multipartFile, "image");
        String videoUrl = s3Uploader.upload(multipartVideo, "video");

        User user = userDetails.getUser();
        videoService.uploadVideo(videoRequestDto, imgUrl, user, videoUrl);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/video")
    public List<VideoResponseDto> getVideosInfo() {
        return videoService.getVideosInfo();
    }

    @GetMapping("/video/{video_id}")
    public VideoDetailResponeDto detailVideo(@PathVariable Long video_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return videoService.detailVideo(video_id, user);
    }

    @DeleteMapping("/video/{video_id}")
    public ResponseEntity deleteVideo(@PathVariable Long video_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return videoService.deleteVideo(video_id, user);
    }
}
