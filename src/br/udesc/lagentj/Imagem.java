package br.udesc.lagentj;

import javax.swing.ImageIcon;

import br.udesc.lagentj.suporte.LoadImage;

public class Imagem extends ObjetoDoMundoAdapter {

	private String source;
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public void executar() throws Exception {
	}

	@Override
	public ImageIcon criarImagem() {
		return LoadImage.getInstance().getIcon(source);
	}

	@Override
	public String toString() {
		return "Imagem:"+source;
	}
}
