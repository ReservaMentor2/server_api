package com.reservamentor.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homepage")
@PreAuthorize("hasAnyRole('ESTUDIANTE', 'ADMIN')")
public class HomepageController {

//    @GetMapping("/homepage")
//    public ResponseEntity<List<MentorHomePageDTO>> getHomepage(){
//
//        MentorHomePageDTO mentorHomePageDTO = new MentorHomePageDTO();
//
//        return new ResponseEntity<List<MentorHomePageDTO>>(mentorHomePageDTO, HttpStatus.CREATED);
//    }
}
