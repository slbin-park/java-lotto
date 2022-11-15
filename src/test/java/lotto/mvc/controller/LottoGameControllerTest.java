package lotto.mvc.controller;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.helper.stub.StubLottoGameController;
import lotto.mvc.util.LottoGameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestMethodOrder(OrderAnnotation.class)
class LottoGameControllerTest {

    @Nested
    @DisplayName("process 메소드는")
    class processMethodTest {

        private final LottoGameController controller = new StubLottoGameController();

        @ParameterizedTest
        @CsvSource(
                value = {
                    "GUIDE_PURCHASE_AMOUNT:PROCESS_PURCHASE_LOTTO",
                    "PROCESS_PURCHASE_LOTTO:GUIDE_WINNING_NUMBERS",
                    "GUIDE_WINNING_NUMBERS:PROCESS_WINNING_LOTTO",
                    "PROCESS_WINNING_LOTTO:GUIDE_BONUS_NUMBER",
                    "GUIDE_BONUS_NUMBER:PROCESS_LOTTO_RANKING",
                    "PROCESS_LOTTO_RANKING:APPLICATION_EXIT",
                    "APPLICATION_EXIT:APPLICATION_EXIT",
                    "APPLICATION_EXCEPTION:APPLICATION_EXCEPTION"
                },
                delimiter = ':'
        )
        @DisplayName("다음 실행 상태 LottoGameStatus를 반환한다.")
        void success_test(LottoGameStatus inputStatus, LottoGameStatus expectedStatus) {
            LottoGameStatus actualStatus = controller.process(inputStatus, "");

            assertThat(actualStatus).isSameAs(expectedStatus);
        }
    }
}