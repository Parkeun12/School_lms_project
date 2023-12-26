package com.example.school_lms.repository;

import com.example.school_lms.entity.LectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LectureDetailRepository extends JpaRepository<LectureDetail, Integer> {
    @Override
    ArrayList<LectureDetail> findAll();
    // 강의 테이블 pk로 강의 리스트 추출해야함
//    @Query("select l from Lecture l where l.course.no = :cno")
//    List<LetureDetail> lectureCnoList(Integer lectureSelId);

//    수정 해야함
//    @Query(value = "select p.product_num, p.product_name, p.product_price, p.product_img, p.product_color, p.product_size, p.product_txt, w.wishlist_id " +
//            "from wishlist w " +
//            "inner join product p " +
//            "on w.product_num=p.product_num " +
//            "where w.users_id = :userId", nativeQuery = true)
//    ArrayList<LetureDetail> findWishlistIdProductNameProductPriceByUserId(@Param("userId") Long userId);
}
