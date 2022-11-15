package lotto.mvc.view;

import lotto.mvc.dto.output.OutputExceptionMessageDto;
import lotto.mvc.dto.output.OutputLottoStatisticsDto;
import lotto.mvc.dto.output.OutputPlayerInfoDto;

public final class OutputView {

    private OutputView() {
    }

    public static void renderPlayerLotto(OutputPlayerInfoDto outputPlayerInfoDto) {
        printWithMessage(outputPlayerInfoDto.getPlayerInfo());
    }

    public static void renderLottoResult(OutputLottoStatisticsDto outputLottoStatisticsDto) {
        printWithMessage(outputLottoStatisticsDto.getLottoStatistics());
    }

    public static void renderExceptionMessage(OutputExceptionMessageDto outputExceptionMessageDto) {
        printWithMessage(outputExceptionMessageDto.getExceptionMessage());
    }

    private static void printWithMessage(String message) {
        System.out.println(message);
    }
}