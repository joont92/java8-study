package joont92;

import java.util.*;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		LottoMachine lottoMachine = new LottoMachine();
		List<Lotto> purchasedLotto;

		while(true){
			try{
				System.out.println("구입 금액을 입력해 주세요.");
				Integer money = scanner.nextInt();

                System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
				Integer manualCnt = scanner.nextInt();

				Purchase availableLottoCnt = new Purchase(money, manualCnt);
				purchasedLotto = lottoMachine.purchase(availableLottoCnt);

				System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.\n", availableLottoCnt.getManualCnt(), availableLottoCnt.getAutoCnt());
				if (purchasedLotto.size() > 0) {
					purchasedLotto.forEach(l -> System.out.println(l.getNumbers()));
					break;
				}

			} catch(InputMismatchException e){
				scanner.nextLine();
				System.out.println("숫자만 입력 가능");
			} finally {
				scanner.nextLine();
			}
		}

		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		Lotto correctLotto = DomainUtil.makeLottoFromStdin();

		System.out.println("보너스 볼을 입력해 주세요.");
		Bonus bonus = DomainUtil.makeBonusFromStdin(correctLotto);

		// 당첨여부 확인!
		lottoMachine.printResult(lottoMachine.draw(correctLotto, bonus, purchasedLotto));
	}
}
