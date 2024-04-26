fun main() {                                      
    val fixedPoint = { f: () -> String -> f() }   

    val programText = fixedPoint(::mainText)      
    println(programText)                          
}

fun mainText(): String {                         
    val fileName = "C:/Users/Administrator/Documents/Prolog/text_program.kt"                      
    val file = java.io.File(fileName)             
    return file.readText()                        
}