package com.github.fbdo.pricing

import java.io._

object FileUtils {

    def getScriptFolder(exampleName:String): File = {
        val folder = new File("src/main/resources").getAbsoluteFile
        val exampleFolder = new File(folder, exampleName)
        if (!exampleFolder.exists) {
            throw new RuntimeException("The target folder does not exist, please create folder " + exampleFolder + " first")
        }

        return exampleFolder
    }
}
