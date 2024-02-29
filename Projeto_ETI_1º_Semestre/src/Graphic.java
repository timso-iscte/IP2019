class Graphic {

	private final Color BLACK = new Color(0, 0, 0);
	public ColorImage graph;
	public String graphTitle;
	public String abscissaName;
	public String ordinateName;

//m�todo construtor
	Graphic(ColorImage graphic) {
		this.graph = graphic;
	}

//m�todo construtor
	Graphic(ColorImage graphic, String Title, String abscissaName, String ordenateName) {
		this.graph = graphic;
		this.graphTitle = Title;
		this.abscissaName = abscissaName;
		this.ordinateName = ordenateName;
	}

//fun��es para definir atributos
	void setGraphicTitle(String name) {
		this.graphTitle = name;
	}

	void setAbscissaName(String name) {
		this.abscissaName = name;
	}

	void setOrdinateName(String name) {
		this.ordinateName = name;
	}

//fun��o para aplicar transparencia
	void setTransparent() {

		ColorImage c = this.graph;
		for (int i = 0; i != c.getWidth(); i++) {
			for (int j = 0; j != c.getHeight(); j++) {
				if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
					c.setColor(i, j, BLACK);
				}
			}

		}
		this.graph = c;

	}

//fun��es para obter informa��o textual associada ao gr�fico
	ColorImage getImage() {
		return this.graph;
	}

	String getGraphicTitle() {
		return this.graphTitle;
	}

	String getAbscissaName() {
		return this.abscissaName;
	}

	String getOrdenateName() {
		return this.ordinateName;
	}

	String getAllTextualInformation() {
		return "Graphic Title :" + this.graphTitle + "    Abscissa's Name:" + this.abscissaName + "    Ordenate's Name:"
				+ this.ordinateName;
	}

//graficos
	static ColorImage graphic = StaticClass.testCreateGraphic();

	static void test() {
		Graphic g = new Graphic(graphic);
		return;
	}
}