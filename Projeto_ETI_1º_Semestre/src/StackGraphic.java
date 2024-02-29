class StackGraphic {

	private final int MAXSTACK = 10;
	public Graphic[] stack = new Graphic[MAXSTACK];
	private int next = 0;

//função para manter todas as posições ocupadas seguindo o vetor (a começar em stack[0], sem posiçoes intermediarias null ate stack[MAXSTACK])
	void shift(Graphic[] vec) {
		for (int i = 0; i != vec.length - 1; i++) {
			if (vec[i] == null) {
				vec[i] = vec[i + 1];
				vec[i + 1] = null;
			}
		}
	}

//funções auxiliares
	boolean isFull() {
		if (next >= MAXSTACK) {
			return true;
		} else
			return false;
	}

	boolean isEmpty() {
		if (next == 0) {
			return true;
		} else
			return false;
	}

	boolean isTitleNull(Graphic[] vec, int a) {
		if (vec[a].getGraphicTitle() == null) {
			return true;
		} else
			return false;
	}

//método construtor
	StackGraphic(Graphic graphic) {
		this.stack[0] = graphic;
		next++;
	}

//método construtor
	StackGraphic(Graphic[] graphicVec) {
		if (graphicVec.length <= MAXSTACK) {
			for (int i = 0; i != graphicVec.length; i++) {
				this.stack[i] = graphicVec[i];
				next++;
			}
			shift(this.stack);
		} else
			throw new IllegalArgumentException(
					"Invalid vector length: " + graphicVec.length + " vector length should be < " + MAXSTACK + "");
	}

//função para adicionar varios graficos de uma vez ao stack
	void addStack(Graphic[] graphicVec) {
		shift(this.stack);
		if (this.isFull() == false) {
			int j = 0; // para iterar o grafico dado
			for (int i = 0; i != MAXSTACK; i++) {
				if (this.stack[i] == null && j != graphicVec.length) {
					this.stack[i] = graphicVec[j];
					j++;
					next++;
				}
			}
			shift(this.stack);
		} else
			throw new IllegalStateException("The stack is full");
	}

//função para adicionar um gráfico ao topo do stack
	void addToTop(Graphic graphic) {
		if (this.isFull() == false) {
			this.stack[next] = graphic;
			next++;
		} else
			throw new IllegalStateException("the stack is full");
	}

//função para remover um gráfico do topo do stack
	void removeTop() {
		if (this.isEmpty() == false) {
			this.stack[next - 1] = null;
			next--;
		} else
			throw new IllegalStateException("The stack is empty");
	}

//função para obter o gráfico no topo do stack
	Graphic getTop() {
		if (next > 0) {
			return this.stack[next - 1];
		} else
			throw new IllegalStateException("The stack is empty");
	}

//função para adicionar um gráfico a uma posição do stack, menor que o topo
	void addToPosition(int position, Graphic graphic) {
		if (this.isFull() == false) {
			if (position < next) {
				for (int i = MAXSTACK - 1; i >= position && i != 0; i--) {
					this.stack[i] = this.stack[i - 1];
				}
				this.stack[position] = graphic;
				next++;
			} else
				throw new IllegalArgumentException("The postition should be inferior to the top of the stack");
		} else
			throw new IllegalStateException("This stack is full");

	}

//função para trocar dois gráficos de duas posições do stack
	void changeTwoPositions(int position1, int position2) {
		if (this.stack[position1] != null && this.stack[position2] != null) {
			Graphic c = this.stack[position1];
			this.stack[position1] = this.stack[position2];
			this.stack[position2] = c;
		}
	}

//função que devolve um vetor contendo os gráficos com titulo null
	Graphic[] getNoTitle() {
		int x = 0;
		for (int i = 0; i != next; i++) {
			if (stack[i].getGraphicTitle() == null) {
				x++;
			}
		}
		Graphic[] noTitleVec = new Graphic[x];
		int j = 0;
		for (int i = 0; i != next; i++) {
			if (stack[i].getGraphicTitle() == null) {
				noTitleVec[j++] = this.stack[i];
			}
		}
		return noTitleVec;
	}

//função que devolve um vetor contendo os gráficos ordenados alfabéticamente
	void getAlphabeticOrder() {
		/*
		 * Graphic[] orderVec = new Graphic[next]; for (int i = 0; i != next; i++)
		 * orderVec[i] = this.stack[i];
		 */ int x = 0;
		for (int i = next - 1; i >= 0; i--) {
			if (isTitleNull(this.stack, i) == true) {
				Graphic p = this.stack[i];
				this.stack[i] = null;
				shift(this.stack);
				this.stack[next - 1] = p;
				x++; // para contar os nulls que vão para o fim do vetor
			}
		}
		for (int i = 0; i != next - x; i++) {
			for (int j = i + 1; j < next - x; j++) {
				String si = this.stack[i].getGraphicTitle();
				String sj = this.stack[j].getGraphicTitle();
				// if (si != null && sj != null)
				if (si.charAt(0) > sj.charAt(0)) {
					Graphic a = this.stack[i];
					this.stack[i] = this.stack[j];
					this.stack[j] = a;
				}
			}
		}
	}

//função que devolve a imagem resultante da sobreposição dos objetos do stack
	ColorImage getOverlap() {
		int width = 0;
		int height = 0;
		for (int i = 0; i != next; i++) {
			if (this.stack[i].getImage().getWidth() > width)
				width = this.stack[i].getImage().getWidth();
			if (this.stack[i].getImage().getHeight() > height)
				height = this.stack[i].getImage().getHeight();
		}
		ColorImage overlap = new ColorImage(width, height);
		for (int x = 0; x != next; x++) { // para iterar o stack
			for (int i = 0; i != this.stack[x].getImage().getWidth(); i++)
				for (int j = 0; j != this.stack[x].getImage().getHeight(); j++) {
					if (this.stack[x].getImage().getColor(i, j).getR() != 0
							|| this.stack[x].getImage().getColor(i, j).getG() != 0
							|| this.stack[x].getImage().getColor(i, j).getB() != 0) {
						overlap.setColor(i, j + (height - this.stack[x].getImage().getHeight()),
								this.stack[x].getImage().getColor(i, j));
					}
				}
		}
		return overlap;
	}

//função que devolve a imagem resultante da sobreposição dos objetos rodada 90°
	ColorImage getOverlapRotate() {
		ColorImage overlap = getOverlap();
		ColorImage overlapRotate = new ColorImage(overlap.getHeight(), overlap.getWidth());
		for (int i = 0; i != overlap.getWidth(); i++) {
			for (int j = 0; j != overlap.getHeight(); j++) {
				overlapRotate.setColor(overlapRotate.getWidth() - 1 - j, i, overlap.getColor(i, j));
			}
		}
		return overlapRotate;
	}

//graficos
	private static Graphic a = new Graphic(StaticClass.testCreateGraphic(), "a_grafico", "", "");
	private static Graphic b = new Graphic(StaticClass.testCreateGraphicGradient(), "b_grafico", "", "");
	private static Graphic c = new Graphic(StaticClass.testCreateDispersionGraphic(), "c_grafico", "", "");
	private static Graphic d = new Graphic(StaticClass.testCreateDispersionGraphic());
	private static Graphic e = new Graphic(StaticClass.testRotateGraphic90());

	private static Graphic[] v = { a, d, a, a, d, d, a, d, a };
	private static Graphic[] u = { b, b, b, b, b, b, b, b, b };
	private static Graphic[] z = { b, a, b, a, b, a, b, b, b };
	private static Graphic[] p = { b, a, b, a, a, c };
	private static Graphic[] k = { d, a, b, c };
	private static Graphic[] m = { a, c };

//função teste
	static void test() {

		StackGraphic g = new StackGraphic(k);
//		g.addStack(k);
//		g.removeTop();
//		g.addToPosition(0, a);
//		g.getNoTitle();
//		g.getAlphabeticOrder();
//		g.getOverlap();
//		g.getOverlapRotate();
		return;
	}
}