package com.example.school_lms.controller;

import com.example.school_lms.dto.LectureDetailDto;
import com.example.school_lms.entity.LectureDetail;
import com.example.school_lms.repository.LectureDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LectureDetailController {
    @Autowired
    public LectureDetailRepository lectureDetailRepository;

    // 상세 강의 등록 페이지
    @GetMapping("/lectureDetail/new")
    public String lectureDetailSave(Model model){

        model.addAttribute("lectureDetailDto", new LectureDetailDto());

        return "lecture_detailNew";
    }

//     강의 등록 (post)
    @PostMapping("/lectureDetail/create")
    public String createLectureDetail(@ModelAttribute LectureDetailDto lectureDetailDto){

        log.info(lectureDetailDto.toString());
        //1. DTO > Entity로 변환
        LectureDetail lectureDetail = lectureDetailDto.toEntity();
        log.info(lectureDetail.toString());
        //2. 변환된 Entity를 Repository를 통해서 DB에 저장
        LectureDetail saved = lectureDetailRepository.save(lectureDetail);
        log.info(lectureDetailDto.toString());

        return "redirect:/lecture_detail/" + saved.getLectureSelId();
    }
    @GetMapping("/lectureDetail/{lectureSelId}") //여기 수정해야됨
    //ULR 요청이 오면 전달받은 id 값을 매개변수에 대응해서 넘겨줌
    public String show(@PathVariable Integer lectureSelId, Model model)
    {
        log.info("lectureSelId = " + lectureSelId);
        // 1. 전달받은 id 값으로 조회 > 데이터 가져오기
        LectureDetail lectureDetailEntity = lectureDetailRepository.findById(lectureSelId).orElse(null);
        // 2. 모델에 데이터 등록하기
        //조회를 통해서 가져온 데이터(엔티티)를 모델에 등록하기
        //addAttribute(등록 이름, 등록 데이터)
        log.info("lectureDetailEntity = " + lectureDetailEntity); // 로그 추가

        model.addAttribute("lectureDetail", lectureDetailEntity);
        // 3. 뷰 페이지 반환
        return "lecture_detailShow";
    }

    @GetMapping("/lectureDetail")
    public String index(Model model)
    {
        //1. 디비에서 Article 테이블에 있는 모든 데이터 가져오기
        ArrayList<LectureDetail> lectureDetailEntityList = lectureDetailRepository.findAll();
        //Iterable, Collection, List ... 상하 관계를 지님
        //따라서 Iterable 이라는 상위 타입으로 캐스팅 해줌
        //낮 > 높 = 업캐스팅
        //높 > 낮 = 다운캐스팅

        //2. Article 묶음을 모델에 등록( Entity > Model )
        model.addAttribute("lectureDetailList", lectureDetailEntityList);
        //3. 뷰에 모델 뿌리기(표시)
        return "lecture_detailList";
    }

    //    ---------------------------------------------------------------------------
    // 특정 강의 수정하기
//    @GetMapping("/lectureDetail/{lectureSelId}/edit")
//    public String edit(@PathVariable Integer lectureSelId, Model model)
//    {
//        //1. 수정할 데이터 가져오기
//        LectureDetail lectureDetailEntity = lectureDetailRepository.findById(lectureSelId).orElse(null);
//        //2. 모델에 데이터 등록
//        model.addAttribute("lectureDetail", lectureDetailEntity);
//
//        return "lecture_detailEdit";
//    }

//    @PostMapping("/lectureDetail/update")
//    public String update(LectureDetailDto form)
//    {
//        log.info(form.toString());
//        //1. DTO > 엔티티
//        LectureDetail lectureDetail = form.toEntity();
//        //폼에서 넘겨받은 데이터를 엔티티로 변환후에
//        //해당 타겟에 대한 정보로 디비에 접근(ID로 조회)
//        LectureDetail UpdateTarget = lectureDetailRepository.findById(lectureDetail.getLectureSelId()).orElse(null);
//        //2. 엔티티 > 디비로
//        if(UpdateTarget != null)
//        {
//            //수정 대상이 존재할 경우에만(null이 아닌경우) 수정 or 저장
//            lectureDetailRepository.save(lectureDetail);
//        }
//        //3. 디비 저장 후에 리다이렉트
//        return "redirect:/lecture_detail/" + lectureDetail.getLectureSelId();
//    }

    // 특정 강의 삭제
//    @GetMapping("/lectureDetail/{lectureSelId}/delete")
//    public String Delete(@PathVariable Integer lectureSelId, RedirectAttributes ra){
//        //삭제할 대상 가져오기
//        LectureDetail DeleteTarget = lectureDetailRepository.findById(lectureSelId).orElse(null);
//        //대상 엔티티 삭제
//        if(DeleteTarget != null)
//        {
//            //삭제 대상이 존재할 경우에만(null이 아닌경우) 삭제
//            lectureDetailRepository.delete(DeleteTarget);
//            //addFlashAttribute(키 문자열, 값 문자)
//            ra.addFlashAttribute("msg", "Delete Complete");
//        }
//        // 결과화면 리다이렉트
//        return "redirect:/lectureDetail";
//    }
}
