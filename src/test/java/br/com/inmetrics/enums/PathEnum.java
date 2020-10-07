package br.com.inmetrics.enums;

import java.util.Objects;

import br.com.inmetrics.exeptions.EnumValidationException;

public enum PathEnum {

    POST_CADASTRAR("/empregado/cadastrar"),
    PUT_EMPREGADO_ALTERAR("/empregado/alterar/%s");

    private String path;

    private PathEnum( String path ) {
        this.path = path;
    }

    public static String getPath( String path ) throws EnumValidationException {

        if (Objects.isNull( path ))
            throw new EnumValidationException( path, "PathEnum" );
        for (PathEnum r : PathEnum.values()) {
            if (path.equals( r.name() ))
                return r.getPath();
        }
        throw new EnumValidationException( path, "PathEnum" );
    }

    public String getPath() {
        return path;
    }
}
