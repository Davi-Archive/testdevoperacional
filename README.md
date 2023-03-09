# Teste Dev./Operacional

## Erros Encontrados

1. Regras de negócio:
> O Cliente está podendo comprar de outra empresa após a seleção.

> O Saldo total de todas as vendas não está contabilizando a comissão.

2. Código:
> As classes estão muito grandes e de difícil leitura, oque dificulta a manutenção.

> Muitas estruturas condicionais que poderiam ser substituídas.

> Repetição de código, principalmente em prints.

> Falta de Reuso para métodos similares

3. Boas Práticas
> A Classe Main tem responsabilidade demais.

> A persistência de dados está acoplada com seus casos de uso.

> Muitos métodos stream aglomerados.

> Venda acomplada a classe Main.

> Casos de uso de empresa e cliente na mesma classe.

4. Experiência do Usuário
> Falta feedback ao usuário na hora que o mesmo esta fazendo a compra.

> Falta de ordem nos itens listados.

> Mensagem de Senha ou Usuário incorreto, pouco segura.


## Cenário:

Uma empresa solicitou um sistema simples de compra. Nesse sistema o cliente pode fazer compras em algumas empresas de acordo com os produtos que as mesmas têm disponível em estoque, além disso ele também pode ver as suas compras. Por sua vez a empresa pode ver as suas vendas e os seus produtos.
Algumas das regras de negócios são:

1. Cada empresa tem sua taxa (comissão do sistema) para as transações
2. Além do administrador e a própria empresa, nenhum outro usuário poderá ver informações da empresa (além do nome).

3. Ao finalizar uma compra o cliente deve ver um resumo da mesma.

4. O saldo da empresa deve ser alterado já refletindo as taxas

5. A empresa deve vender apenas produtos que ela esteja relacionada.

6. A empresa poderá ver a taxa de comissão de sistema em cada venda (ao listar suas vendas).
