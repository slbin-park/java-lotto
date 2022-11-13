package lotto.view;

import lotto.domain.LottoGroups;
import lotto.domain.Rank;

import java.util.List;

public class OutputView {

    private static final String PRINT_LOTTO_COUNT = "개를 구매했습니다.";

    public void printLottoCount(int count){
        System.out.println(count+PRINT_LOTTO_COUNT);
        System.out.println();
    }

    public void printWinningHistory() {

    }

    public void printLottoNumbers(List<List<Integer>> numbers) {
        numbers.stream()
                .forEach(System.out::println);
    }

    public void printWinningList(List<Integer> winningList) {
        for (int i = 3; i <= 7; i++) {
            System.out.println(Rank.findMoney(i).getMessage(winningList.get(i)));
        }
    }

    public void printErrorMessage(String message) {

    }
}
