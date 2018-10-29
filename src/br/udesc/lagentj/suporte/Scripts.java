package br.udesc.lagentj.suporte;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Scripts {

    private static ScriptEngine js;

    private static InputStream loadScript(String scriptName) throws Exception {
        InputStream script = Scripts.class.getResourceAsStream(scriptName);
        return script;
    }

    static {
        js = (new ScriptEngineManager()).getEngineByName("js");
        try {
            js.eval(new BufferedReader(new InputStreamReader(loadScript("/scripts/agentej.js"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Object eval(String value) throws ScriptException {
        return js.eval(value);
    }
}
