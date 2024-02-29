class StaticClass {
// função para criar graficos 2D
	static ColorImage createGraphic(int[] v, int columnWidth, int columnInterval, Color color) {
		ColorImage graphic = new ColorImage((v.length * columnWidth) + ((v.length + 1) * columnInterval),
				(max(v) + max(v) / 4));
		int x = 0;
		for (int i = columnInterval; i < graphic.getWidth(); i += (columnWidth + columnInterval)) {
			for (int j = graphic.getHeight() - 1; j < graphic.getHeight(); j++) {
				createColumn(graphic, i, graphic.getHeight() - 1, columnWidth, v[x++], color);

			}
		}
		return graphic;
	}

// função teste
	static ColorImage testCreateGraphic() {
		int[] v = { 50, 150, 100, 200, 125 };
		int columnWidth = 50;
		int columnInterval = 25;
		Color color = new Color(255, 0, 0);
		createGraphic(v, columnWidth, columnInterval, color);
		return createGraphicGradient(v, columnWidth, columnInterval, color, 10);
	}

// função auxiliar para crear coluna
	static void createColumn(ColorImage graphic, int x, int y, int columnWidth, int columnHeight, Color color) {
		for (int i = x; i != x + columnWidth && i < graphic.getWidth(); i++) {
			for (int j = y; j != y - columnHeight && j > 0; j--) {
				graphic.setColor(i, j, color);
			}
		}
	}

// função teste
	static void testCreatecolumn() {
		int columnWidth = 50;
		int columnHeight = 100;
		Color color = new Color(255, 50, 255);
		ColorImage image = new ColorImage(100, 150);
		createColumn(image, 25, 149, columnWidth, columnHeight, color);
	}

//função auxiliar para devolver o maior valor contido num array;
	static int max(int[] v) {
		int max = v[0];
		for (int i = 0; i != v.length; i++)
			if (v[i] > max) {
				max = v[i];
			}
		return max;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *
	 *
	 */
//função para criar gráfico 2D com gradiente contido entre 0 e metade da width da coluna
	static ColorImage createGraphicGradient(int[] v, int columnWidth, int columnInterval, Color color, int gradient) {
		ColorImage graphic = createGraphic(v, columnWidth, columnInterval, color);
		gradient(graphic, v, columnWidth, columnInterval, color, gradient);
		return graphic;

	}

// função teste
	static ColorImage testCreateGraphicGradient() {
		int[] v = { 75, 150, 100, 200, 125 };
		int columnWidth = 70;
		int columnInterval = 25;
		Color color = new Color(0, 255, 0);
		int gradient = 15;
		return createGraphicGradient(v, columnWidth, columnInterval, color, gradient);
	}

// função auxiliar para criar gradiente
	static void gradient(ColorImage graphic, int[] v, int columnWidth, int columnInterval, Color color, int gradient) {
		int maxGrad = (columnWidth / 2) - 1; // variável que aparece na exceção
		if (gradient > 0 && gradient <= maxGrad) {
			int r = color.getR() / (gradient + 1);
			int g = color.getG() / (gradient + 1);
			int b = color.getB() / (gradient + 1);
			for (int x = 0; x != v.length; x++) { // iterar as colunas
				for (int gradientPixels = 1; gradientPixels != gradient + 1; gradientPixels++) {
					for (int i = columnInterval + x * columnWidth + x * columnInterval + gradientPixels
							- 1; i <= columnInterval + x * columnWidth + x * columnInterval + gradientPixels - 1; i++) {
						for (int j = graphic.getHeight() - 1; j >= graphic.getHeight() - v[x] + gradientPixels; j--) {
							Color a = new Color(r * gradientPixels, g * gradientPixels, b * gradientPixels);
							graphic.setColor(i, j, a);// gradiente à esquerda
							graphic.setColor(2 * (columnInterval * x + columnWidth * x) + columnInterval + columnWidth
									+ columnInterval - i - 1, j, a);// gradiente à direita
						}
					}
					for (int i0 = columnInterval + x * columnWidth + x * columnInterval + gradientPixels
							- 1; i0 != columnInterval + columnWidth + x * columnWidth + x * columnInterval
									- gradientPixels + 1; i0++) {
						for (int j0 = graphic.getHeight() - v[x] + gradientPixels - 1; j0 <= graphic.getHeight() - v[x]
								+ gradientPixels - 1; j0++) {
							Color a = new Color(r * gradientPixels, g * gradientPixels, b * gradientPixels);
							graphic.setColor(i0, j0, a);// gradiente em cima
						}
					}
				}
			}
		} else
			throw new IllegalArgumentException("invalid gradient value: " + gradient
					+ " Please choose a gradient value between 0 and " + maxGrad + "");
		// o gradiente só é aceite entre 0 e metade da width
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

//função para criar grafico de dispersao

	static ColorImage createDispersionGraphic(int[] v, int radius, int circleInterval, Color color) {
		ColorImage graphic = new ColorImage((v.length + 1) * circleInterval + v.length * (2 * radius),
				max(v) + max(v) / 4);
		int x = 0;
		for (int i = circleInterval + radius; i < graphic.getWidth(); i += circleInterval + 2 * radius) {
			drawCircle(graphic, i, graphic.getHeight() - v[x++], radius, color);

		}
		return graphic;
	}

//função teste

	static ColorImage testCreateDispersionGraphic() {
		int[] v = { 50, 150, 100, 200, 125 };
		int radius = 25;
		int circleInterval = 25;
		Color color = new Color(0, 255, 255);
		createDispersionGraphic(v, radius, circleInterval, color);
		return rotateGraphic90(createDispersionGraphic(v, radius, circleInterval, color));

	}

//função para criar circulos

	static void drawCircle(ColorImage graphic, int x, int y, int radius, Color color) {
		for (int i = x - radius; i <= x + radius; i++) {
			for (int j = y - radius; j <= y + radius; j++) {
				if ((radius * radius) >= ((i - x) * (i - x) + (j - y) * (j - y))) {
					graphic.setColor(i, j, color);
				}

			}
		}
	}

//função teste
	static ColorImage testDrawCircle() {
		ColorImage graphic = new ColorImage(50, 50);
		int x = 25;
		int y = 25;
		int radius = 16;
		Color color = new Color(255, 0, 0);
		drawCircle(graphic, x, y, radius, color);
		return graphic;
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
//função para rodar 90° no sentido do ponteiro dos relógios 

	static ColorImage rotateGraphic90(ColorImage graphic) {
		ColorImage graphic2 = new ColorImage(graphic.getHeight(), graphic.getWidth());
		for (int i = 0; i != graphic.getWidth(); i++) {
			for (int j = 0; j != graphic.getHeight(); j++) {
				graphic2.setColor(graphic2.getWidth() - 1 - j, i, graphic.getColor(i, j));
			}
		}
		return graphic2;
	}

//função teste
	static ColorImage testRotateGraphic90() {
		int[] v = { 100, 150, 75 };
		int columnWidth = 50;
		int columnInterval = 40;
		Color color = new Color(255, 0, 0);
		int gradient = 15;
		ColorImage graphic = createGraphicGradient(v, columnWidth, columnInterval, color, gradient);
		graphic = rotateGraphic90(graphic);
		return graphic;
	}

}