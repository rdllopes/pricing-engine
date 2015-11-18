package com.github.fbdo.pricing.groovy

import com.github.fbdo.pricing.FileUtils
import com.github.fbdo.pricing.PricingEngine
import groovy.lang.GroovyClassLoader
import groovy.lang.GroovyObject

import java.io.File
import java.math.BigDecimal
import java.util.Map

class GroovyPricingEngine extends PricingEngine {


    @Override
    def getPrice(attributes: Map[String, Object]):BigDecimal = {
        val parent = getClass().getClassLoader()
        val loader = new GroovyClassLoader(parent)
        var groovyObject = ???

        try {
            val groovyClass = loader.parseClass(new File(FileUtils.getScriptFolder("groovy"), attributes.get("event") + ".groovy"))
            groovyObject = groovyClass.newInstance()

        } catch  {
            case e:Exception => throw new RuntimeException(e)
        }
        return groovyObject.invokeMethod("run", Array(attributes))
    }


}
