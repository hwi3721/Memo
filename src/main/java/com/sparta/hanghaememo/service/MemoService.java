package com.sparta.hanghaememo.service;


        import com.sparta.hanghaememo.dto.MemoRequestDto;
        import com.sparta.hanghaememo.entity.Memo;
        import com.sparta.hanghaememo.repository.MemoRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;


@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Memo getMemo(Long id) {
        Memo target = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return target;
    }

    @Transactional
    public Memo update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (memo.getPassword().equals(requestDto.getPassword())) {
            memo.update(requestDto);
        }
        return memo;
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
