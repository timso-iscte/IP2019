class funçoes lixo
{

	static void gradient1(ColorImage graphic, int[] v, int columnWidth, int columnInterval, int gradient) {
		Color black = new Color(0, 0, 0);
		for (int columnN = columnInterval; columnN < graphic.getWidth(); columnN += columnInterval
				+ columnWidth) { /* para iterar colunas */
			int grad = 2;
			int grad1 = 2;
			int grad2 = 2;
			while (grad <= gradient + 1 && grad1 <= gradient + 1 && grad2 <= gradient + 1)
				for (int i = columnN; i <= columnN + gradient; i++) {
					for (int j = graphic.getHeight() - 1; j >= 0; j--) {
						if (graphic.getColor(i, j) != black && gradient > 0) {
							Color a = graphic.getColor(i, j); /* cor do pixel */
							Color b = new Color(a.getR() - (a.getR() / grad), a.getG() - (a.getG() / grad),
									a.getB() - (a.getB() / grad)); /* cor do pixel com o gradiente aplicado */
							graphic.setColor(i, j, b);

						}

					}
					grad++;
				}
			for (int i1 = graphic.getWidth() - 1 - columnN; i1 >= graphic.getWidth() - 1 - columnN - gradient; i1--) {
				for (int j1 = graphic.getHeight() - 1; j1 >= 0; j1--) {
					if (graphic.getColor(i1, j1) != black && gradient > 0) {
						Color a = graphic.getColor(i1, j1); /* cor do pixel */
						Color b = new Color(a.getR() - (a.getR() / grad1), a.getG() - (a.getG() / grad1),
								a.getB() - (a.getB() / grad1)); /* cor do pixel com o gradiente aplicado */
						graphic.setColor(i1, j1, b);

					}

				}
				grad1++;
			}

			for (int x = 0; x != v.length; x++) {
				int grad3 = 2;
				while (grad3 <= gradient + 1) {
					for (int j2 = graphic.getHeight() - 1 - v[x]; j2 <= graphic.getHeight() - 1 - v[x]
							+ gradient; j2++) {

						for (int i2 = columnInterval + x * columnWidth + x * columnInterval; i2 <= columnInterval
								+ x * columnWidth + x * columnInterval + columnWidth; i2++) {
							if (graphic.getColor(i2, j2) != black && gradient > 0) {
								Color a = graphic.getColor(i2, j2); /* cor do pixel */
								Color b = new Color(a.getR() - (a.getR() / grad3), a.getG() - (a.getG() / grad3),
										a.getB() - (a.getB() / grad3)); /* cor do pixel com o gradiente aplicado */
								graphic.setColor(i2, j2, b);

							}
						}
						grad3++;
					}

				}

			}

		}
	}

	static void gradient(ColorImage graphic, int v[], int columnWidth, int columnInterval, int gradient, Color color) {
		Color black = new Color(0, 0, 0);
		int r = color.getR() / gradient;
		int g = color.getG() / gradient;
		int b = color.getB() / gradient;
		int y = 0;
		for (int columnN = columnInterval; columnN < graphic.getWidth(); columnN += columnInterval
				+ columnWidth) { /* para iterar colunas */
			int x = 0;
			int grad = 1;
			while (grad < gradient - 1) {
				for (int i = columnN + x; i <= columnN + x; i++) {
					int m = 0;
					for (int i1 = columnN + columnWidth - x; i1 >= columnN + columnWidth - x; i1--) {
						for (int j = graphic.getHeight() - 1 + m; j >= 0; j--) {
							if (graphic.getColor(i, j) != black && gradient > 0) {
								Color a = new Color(grad * r, grad * g,
										grad * b); /* cor do pixel com o gradiente aplicado */
								graphic.setColor(i, j, a);
								graphic.setColor(i1, j, a);

							}
						}
					}
					m++;
				}
				// grad++;
				x++;
				// int grad3 = 1;
				int n = 0;
				// while (grad3 < gradient) {
				for (int j2 = graphic.getHeight() - 1 - v[y]; j2 <= graphic.getHeight() - 1 - v[y] + gradient; j2++) {
					for (int i2 = columnInterval + y * columnWidth + y * columnInterval + n; i2 <= columnInterval
							+ y * columnWidth + y * columnInterval + columnWidth - n; i2++) {
						if (graphic.getColor(i2, j2) != black && gradient > 0) {
							Color a = new Color(grad * r, grad * g, grad * b);
							graphic.setColor(i2, j2, a);

						}
					}

					n++;
				}
				grad++;
			}
		}
		y++;
	}
	// }

	static void gradient1(ColorImage graphic, int v[], int columnWidth, int columnInterval, int gradient) {
		Color black = new Color(0, 0, 0);
		for (int x = 0; x != v.length; x++) {
			for (int grad = 0; grad != gradient; grad++) {
				for (int i = columnInterval + x * columnWidth + x * columnInterval + grad; i >= columnInterval
						+ x * columnWidth + x * columnInterval + grad; i++) {
					for (int i0 = columnInterval + x * columnWidth + x * columnInterval + columnWidth
							- grad; i0 >= columnInterval + x * columnWidth + x * columnInterval + columnWidth
									- grad; i0--) {
						for (int j = graphic.getHeight() - v[x] + grad; j != graphic.getHeight(); j++) {
							if (graphic.getColor(i, j) != black && gradient > 0) {
								Color a = graphic.getColor(i, j); /* cor do pixel */
								Color b = new Color(a.getR() - ((gradient - grad) * (a.getR() / gradient)),
										a.getG() - ((gradient - grad) * (a.getG() / gradient)),
										a.getB() - ((gradient - grad)
												* (a.getB() / gradient))); /* cor do pixel com o gradiente aplicado */
								graphic.setColor(i, j, b);
								graphic.setColor(i0, j, b);
							}
						}

					}
				}
			}
		}
	}
}