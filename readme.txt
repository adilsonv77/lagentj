1.05 (22/09/2018)
- Faltou colocar os métodos getInt() e getInt(Direcao) em ObjetoDoMundo
- Pode-se estipular o valor -1 aos limites para calcular 
automaticamente ficar nas laterais de mundos variáveis
- Pode-se estipular o valor -2 ao limiteSupX e limiteSupY para calcular 
automaticamente não estar nas laterais de mundos variáveis

1.04 (22/08/2018)
- Transferidos os métodos getInt() e getInt(Direcao) do AgenteJ para 
ObjetoDoMundoAdapter

1.03 (16/08/2018)
- Novo método iniciar que permite informar o número do socket
- Novas imagens
- Reestruturada a classe Numero e eliminada Tesouro 
- Projetil eh removido quando chega ao fim

1.02 (14/08/2018)
- Novo método getInt(Direcao):int

1.01 (07/08/2018)
- Atribuída uma cor para as gridlines da JTable, pois no Mac o padrão é branco.

1.0 (31/07/2018)
- Nova classe para o XML: Imagem
- Novo método MundoVisual.removerAtributo
- Migração do Furbot 1.93 para AgenteJ 1.0
- Novo método invocarApos(segundos, nome do metodo)
- Novo método colidido(ObjetoDoMundo)
- Novo método MundoVisual.iniciarImediatamente
- Novo método trocarImagem() para avisar que a imagem precisa mudar, e então ~
chama novamente buildImage().
- Quando usarLinhasNaGrade=false já elimina de uma vez as linhas.
