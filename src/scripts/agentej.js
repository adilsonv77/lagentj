var AgenteJ = {};

AgenteJ.getInt = function (x, y) {
    return Packages.br.udesc.lagentj.objetivos.GoalManager.getInt(x, y);
};

AgenteJ.maiorInt = function (x1, y1, x2, y2) {
    var a = AgenteJ.getInt(x1, y1);
    var b = AgenteJ.getInt(x2, y2);
    return (a > b ? a : a < b ? b : "IGUAIS");
}