package lotto;

import lotto.domain.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Game {

    private static final int LOTTO_UNIT = 1000;
    private static LottoMachine lottoMachine;
    private static InputView inputView;
    private static OutputView outputView;

    public Game() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void start() {
        try {
            int money = inputView.inputMoney();
            int lottoCount = money / LOTTO_UNIT;
            lottoMachine = new LottoMachine(lottoCount);
            outputView.printLottoCount(lottoCount);
            outputView.printLottoNumbers(lottoMachine.getLottosNumberList());
            lottoMachine.saveWinningNumber(inputView.inputWinningNumber(),
                    inputView.inputBonusNumber());
            outputView.printWinningList(lottoMachine.getWinningList());
            outputView.printYield(lottoMachine.getYield());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
