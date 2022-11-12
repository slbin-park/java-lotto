package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.BigInteger;
import lotto.helper.util.LottoPurchaseAmountTestUtils;
import lotto.util.message.LottoExceptionMessageUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPurchaseAmountTest {

    @Nested
    @DisplayName("String playerInputAmount를 매개변수로 받는 메소드는")
    class StringConstructorTest {

        @ParameterizedTest
        @ValueSource(strings = {"1000", "15000", "250000"})
        @DisplayName("만약 정상적인 입력을 받는다면 LottoPurchaseAmount를 반환한다.")
        void success_test(String input) {
            LottoPurchaseAmount lottoPurchaseAmount = new LottoPurchaseAmount(input);

            BigDecimal result = LottoPurchaseAmountTestUtils.findPlayerPurchaseAmount(lottoPurchaseAmount);

            assertThat(result.toString()).isEqualTo(input);
        }

        @ParameterizedTest
        @ValueSource(strings = {"10001", "10010", "10100"})
        @DisplayName("만약 천원 단위가 아닌 입력을 받는다면 IllegalArgumentException 예외가 발생한다.")
        void invalid_amount_unit_exception_test(String invalidInput) {
            assertThatThrownBy(() -> new LottoPurchaseAmount(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoExceptionMessageUtils.INVALID_PURCHASE_AMOUNT_UNIT
                            .findExceptionMessage(invalidInput));
        }

        @ParameterizedTest
        @ValueSource(strings = {"a0000", " 0000", "@0000"})
        @DisplayName("만약 입력 값을 숫자로 치환할 수 없다면 IllegalArgumentException 예외가 발생한다.")
        void invalid_number_format_exception_test(String invalidInput) {
            assertThatThrownBy(() -> new LottoPurchaseAmount(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoExceptionMessageUtils.INVALID_NUMBER_FORMAT
                            .findExceptionMessage(invalidInput.charAt(0)));
        }
    }

    @Nested
    @DisplayName("calculatePurchaseLottoAmount 메소드는")
    class CalculatePurchaseLottoAmountMethodTest {

        @ParameterizedTest
        @CsvSource(
                value = {
                    "1000:1",
                    "5000:5",
                    "10000:10",
                },
                delimiter = ':'
        )
        @DisplayName("만약 호출하면 로또 구매 장수를 반환한다.")
        void success_test(String playerInput, long amountCount) {
            LottoPurchaseAmount lottoPurchaseAmount = new LottoPurchaseAmount(playerInput);

            BigInteger result = lottoPurchaseAmount.calculatePurchaseLottoAmount();

            assertThat(result).isEqualTo(BigInteger.valueOf(amountCount));
        }
    }
}