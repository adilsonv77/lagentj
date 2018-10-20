/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.objetivos.singletons.RestartInterface;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class UsarMetodo extends Objetivo implements RestartInterface {

    private int calls;
    private List<Object> returns;
    private boolean invalid;

    public UsarMetodo(ObjetivoConfiguracao config) {
        super(config);
        invalid = false;
        returns = new ArrayList();
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        if (hasMethod() && calls > 0) {
            return !invalid && isReturnsOK();
        }
        return false;
    }

    @Override
    public String getDescricao() {
        String parametros = "";
        for (int i = 0; i < config.getParametros().size(); i++) {
            Parametro p = config.getParametros().get(i);
            String tipo = "";
            if (p.getTipo().contains(".")) {
                String a = p.getTipo().replace('.', '-');
                String[] b = a.split("-");
                tipo = b[b.length - 1];
            } else {
                tipo = p.getTipo();
            }
            tipo = tipo.replace(";", "");
            parametros += tipo;
            if (i < config.getParametros().size() - 1) {
                parametros += ", ";
            }
        }
        String extra = config.getDescricao() != null ? "O resultado do m\351todo dever\341 ser: '" + config.getValor() + "', ou seja, " + config.getDescricao() : "";
        String descricao = String.format("O agente precisa usar o m\351todo com a seguinte assinatura: 'private %s %s (%s)'. %s", config.getRetorno(), config.getNome(), parametros, extra);
        if (config.isRestrito()) {
            descricao += "Os " + config.getTipoComando().descricao + " s\363 podem ser chamados de dentro deste m\352todo.";
        }
        return descricao;
    }

    public void call() {
        calls++;
    }

    public void retorno(Object info) {
        returns.add(info);
    }

    public boolean hasMethod() {
        boolean hasMethod = false;
        Class obj = null;
        try {
            obj = Class.forName(getMundo().getMundoAgenteJ().getExercicio().getClazz());
            for (Method m : obj.getDeclaredMethods()) {
                if (m.getName().equals(config.getNome())) {
                    boolean hasAllParamTypes = true;
                    for (Class param : m.getParameterTypes()) {
                        boolean found = false;
                        for (Class param2 : config.getParametrosTypes()) {
                            if (param.equals(param2)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            hasAllParamTypes = false;
                        }
                    }
                    if (hasAllParamTypes) {
                        if (config.getRetornoType() == m.getReturnType()) {
                            hasMethod = true;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hasMethod;
    }

    public void invalidate() {
        invalid = true;
    }

    public boolean isRestrito() {
        return config.isRestrito();
    }

    public Comando getTipoComando() {
        return config.getTipoComando();
    }

    private boolean isReturnsOK() {
        String className = getMundo().getMundoAgenteJ().getExercicio().getClazz();
        try {
            if (!returns.isEmpty()) {
                Object value = config.getValor();
                Object real = returns.get(returns.size() - 1);
                if (!value.equals(real)) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void restart() {
        invalid = false;
        calls = 0;
        returns.clear();
    }

}
