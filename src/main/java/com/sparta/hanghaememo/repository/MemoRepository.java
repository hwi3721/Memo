package com.sparta.hanghaememo.repository;


        import com.sparta.hanghaememo.dto.MemoRequestDto;
        import com.sparta.hanghaememo.entity.Memo;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();

//    Memo findBy(MemoRequestDto requestDto);

//    Memo findOne(MemoRequestDto requestDto);
}