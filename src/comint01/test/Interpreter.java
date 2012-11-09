package comint01.test;

import comint01.interpreter.*;

public class Interpreter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 1.23 ==> 1.23
		try {
			System.out.println(
					Num._("1.23").eval()
					);
		} catch (Exception e) {
			System.err.println(e);
		}

		// 1 / 0 ==> exception
		try {
			System.out.println(
					DivideBinExpr._(
							Num._("1"),
							Num._("0")
							).eval()
					);
		} catch (Exception e) {
			System.err.println(e);
		}

		// x = 2.34 ==> 2.34
		try {
			System.out.println(
					AssignExpr._(
							Id._("x"),
							Num._("2.34")
							).eval()
					);
		} catch (Exception e) {
			System.err.println(e);
		}

		// x = x ==> exception
		try {
			System.out.println(
					AssignExpr._(
							Id._("x"),
							Id._("x")
							).eval()
					);
		} catch (Exception e) {
			System.err.println(e);
		}

		// x = -(3.45); x ==> -3.45
		try {
			System.out.println(
					ExprSeq._(
							AssignExpr._(
									Id._("x"),
									MinusUnExpr._(
											Num._("3.45")
											)
									),
									Id._("x")
							).eval()
					);
		} catch (Exception e) {
			System.out.println(e);
		}

		// x = y = 3.45; x / y ==> 1
		try {
			System.out.println(ExprSeq._(
					AssignExpr._(
							Id._("x"),
							AssignExpr._(
									Id._("y"),
									Num._("3.45")
									)
							),
							DivideBinExpr._(
									Id._("x"),
									Id._("y")
									)
					).eval()
					);
		} catch (Exception e) {
			System.out.println(e);
		}

		// x = 1; x + (x = 2) ==> 3
		try {
			System.out.println(
					ExprSeq._(
							AssignExpr._(
									Id._("x"),
									Num._("1")),
									PlusBinExpr._(
											Id._("x"),
											AssignExpr._(
													Id._("x"),
													Num._("2")
													)
											)
							).eval()
					);
		} catch (Exception e) {
			System.out.println(e);
		}

		// x = 1; (x = 2) + x ==> 4
		try {
			System.out.println(
					ExprSeq._(
							AssignExpr._(
									Id._("x"),
									Num._("1")
									),
									PlusBinExpr._(
											AssignExpr._(
													Id._("x"),
													Num._("2")
													),
													Id._("x")
											)
							).eval()
					);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
