package br.com.inmetrics.exeptions;

public class EnumValidationException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    private String enumValue;

    private String enumName;
    
    public EnumValidationException( String enumValue, String enumName ) {

        super( enumValue );

        this.setEnumValue( enumValue );
        this.setEnumName( enumName );
    }

    public EnumValidationException( String enumValue, String enumName, Throwable cause ) {

        super( enumValue, cause );

        this.setEnumValue( enumValue );
        this.setEnumName( enumName );
    }

    public String getEnumValue() {
        return enumValue;
    }

    public void setEnumValue( String enumValue ) {
        this.enumValue = enumValue;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName( String enumName ) {
        this.enumName = enumName;
    }
}
