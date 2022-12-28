package com.sparta.hanghaememo.service;


        import com.sparta.hanghaememo.dto.MemoRequestDto;
        import com.sparta.hanghaememo.dto.NewDto;
        import com.sparta.hanghaememo.entity.Memo;
        import com.sparta.hanghaememo.repository.MemoRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.ArrayList;
        import java.util.List;


@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public NewDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        NewDto newDto = new NewDto(memo);

        return newDto;
    }

//    @Transactional(readOnly = true)
//    public List<Memo> getMemos() {
//        return memoRepository.findAllByOrderByCreatedAtDesc();
//    }

//    @Transactional(readOnly = true)
//    public List<NewDto> getMemos() {
//        List<Memo> memos = memoRepository.findAllByOrderByCreatedAtDesc();
//        List<NewDto> newDtos = new ArrayList<>();
//        for (Memo memo : memos){
//            NewDto newDto = new NewDto(memo);
//            newDtos.add(newDto);
//        }
//        return newDtos;
//    }

    @Transactional(readOnly = true)
    public List<NewDto> getMemos() {
        List<NewDto> newDtos = new ArrayList<>();
        try {
            List<Memo> memos = memoRepository.findAllByOrderByCreatedAtDesc();
            for (int i = 0; i < memos.size(); i++) {
                newDtos.add(new NewDto(memos.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();   //예외가 어디서 어떻게 생겼는 지 알려줌
        }
        return newDtos;
    }
    @Transactional
    public NewDto getMemo(Long id) {
        Memo target = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        NewDto newDto = new NewDto(target);

        return newDto;
    }

    @Transactional
    public NewDto update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (memo.getPassword().equals(requestDto.getPassword())) {
            memo.update(requestDto);
        }
        NewDto newDto = new NewDto(memo);
        return newDto;
    }

    @Transactional
    public String deleteMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (memo.getPassword().equals(requestDto.getPassword())) {
            memoRepository.deleteById(id);
            return "삭제되었습니다.";
        }else{
            return "비밀번호가 일치하지 않습니다.";
        }

    }
}
