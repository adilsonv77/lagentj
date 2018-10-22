var AgenteJ = {};

AgenteJ.getInt = function (x, y) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getInt(x, y);
};

AgenteJ.maiorInt = function (x1, y1, x2, y2) {
    var a = AgenteJ.getInt(x1, y1);
    var b = AgenteJ.getInt(x2, y2);
    return (a > b ? a : a < b ? b : a);
};

AgenteJ.nomeDoMes = function (mes) {
    var meses = ['janeiro', 'fevereiro', 'marco', 'abril', 'maio', 'junho', 'julho', 'agosto', 'setembro', 'outubro', 'novembro', 'dezembro']
    return meses[mes - 1];
};

AgenteJ.fatorial = function (value) {
    value = parseInt(value);
    if (value <= 1) {
        return parseInt(1);
    } else {
        return parseInt(value * AgenteJ.fatorial(value - 1));
    }
};

AgenteJ.fibonacci = function (n) {
    n = parseInt(n);
    if (n < 2) {
        return parseInt(n);
    } else {
        return parseInt(AgenteJ.fibonacci(n - 1) + AgenteJ.fibonacci(n - 2));
    }
};

AgenteJ.posicao = function (x1, y1, x2, y2) {
    var a = AgenteJ.getInt(x1, y1);
    var b = AgenteJ.getInt(x2, y2);
    return AgenteJ.concat(a, b);
};

AgenteJ.concat = function (a, b) {
    return a + "," + b;
};

AgenteJ.getCount = function (valor) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getCount(valor);
};

AgenteJ.getElement = function (index, tipo) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getElement(index, tipo);
};

AgenteJ.getElements = function (tipo) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getElements(tipo);
};

AgenteJ.maisLonge = function (lista) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.maisLonge(lista);
};

AgenteJ.getElementLinha = function (elem) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getElementLinha(elem);
};

AgenteJ.getElementColuna = function (elem) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getElementColuna(elem);
};

AgenteJ.getElementPosX = function (index, tipo) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getElementPosX(index, tipo);
};

AgenteJ.getElementPosY = function (index, tipo) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getElementPosY(index, tipo);
};

AgenteJ.isPrimo = function (value) {
    var divisors = 0;
    for (var i = 1; i <= value; i++) {
        if (value % i == 0) {
            divisors++;
        }
    }
    return divisors == 2;
};

AgenteJ.getElementQuadrant = function (index, tipo, xd, yd) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getElementQuadrant(index, tipo, xd, yd);
};

AgenteJ.getQuadrantElements = function (quadrante, tipo, xd, yd) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getQuadrantElements(quadrante, tipo, xd, yd);
};

AgenteJ.somarNumeros = function (lista) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.somarNumeros(lista);
};

AgenteJ.somarNumeros = function (lista) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.somarNumeros(lista);
};