package com.jimenez.app.rutas.models.enums;

public enum Modelos {

    _2024(2024),
    _2023(2023),
    _2022(2022),
    _2021(2021),
    _2020(2020),
    _2019(2019),
    _2018(2018),
    _2017(2017),
    _2016(2016),
    _2015(2015),
    _2014(2014),
    _2013(2013),
    _2012(2012),
    _2011(2011),
    _2010(2010),
    _2009(2009),
    _2008(2008),
    _2007(2007),
    _2006(2006),
    _2005(2005);

    private int anio;

    private Modelos(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }
}
