package br.udesc.lagentj.suporte;

import br.udesc.lagentj.Imagem;
import br.udesc.lagentj.ObjetoDoMundo;

public class ImagemExercicio extends ElementoExercicio {

	private String source;
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public ImagemExercicio() {
        setClazz("br.udesc.lagentj.Imagem");
        setBloqueado(false);
	}
	
    public ImagemExercicio clonar()
            throws InstantiationException, IllegalAccessException     {
    	ImagemExercicio e = (ImagemExercicio)super.clonar();
        e.setSource(source);
        return e;
    }

    public void outrasAtribuicoes(ObjetoMundoImpl obj, ObjetoDoMundo objMundo)
    {
        Imagem img = (Imagem)objMundo;
        img.setSource(source); 
    }


}
