1.05 (22/09/2018)
- Faltou colocar os m�todos getInt() e getInt(Direcao) em ObjetoDoMundo
- Pode-se estipular o valor -1 aos limites para calcular 
automaticamente ficar nas laterais de mundos vari�veis
- Pode-se estipular o valor -2 ao limiteSupX e limiteSupY para calcular 
automaticamente n�o estar nas laterais de mundos vari�veis

1.04 (22/08/2018)
- Transferidos os m�todos getInt() e getInt(Direcao) do AgenteJ para 
ObjetoDoMundoAdapter

1.03 (16/08/2018)
- Novo m�todo iniciar que permite informar o n�mero do socket
- Novas imagens
- Reestruturada a classe Numero e eliminada Tesouro 
- Projetil eh removido quando chega ao fim

1.02 (14/08/2018)
- Novo m�todo getInt(Direcao):int

1.01 (07/08/2018)
- Atribu�da uma cor para as gridlines da JTable, pois no Mac o padr�o � branco.

1.0 (31/07/2018)
- Nova classe para o XML: Imagem
- Novo m�todo MundoVisual.removerAtributo
- Migra��o do Furbot 1.93 para AgenteJ 1.0
- Novo m�todo invocarApos(segundos, nome do metodo)
- Novo m�todo colidido(ObjetoDoMundo)
- Novo m�todo MundoVisual.iniciarImediatamente
- Novo m�todo trocarImagem() para avisar que a imagem precisa mudar, e ent�o ~
chama novamente buildImage().
- Quando usarLinhasNaGrade=false j� elimina de uma vez as linhas.
