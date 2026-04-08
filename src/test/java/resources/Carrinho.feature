#encoding: utf-8
#language: pt

@Test
Funcionalidade: Colocar produto no carrinho de compras

  Cenario: Adicionar item ao carrinho com sucesso
    Dado que acesso o site "https://store.vivo.com.br/"
    E navego até a seção de "Celulares"
    E seleciono o primeiro produto exibido
    E verifico o preço do produto
    Quando adiciono o produto ao carrinho
    Então o carrinho deve conter o item adicionado
    E o total do carrinho deve igual ao valor do produto