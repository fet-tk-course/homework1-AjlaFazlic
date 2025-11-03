import kotlin.collections.set

interface Osoba {
    fun punIdentitet(): String
    fun zemljaPorijekla(): String
}

open class Programer(
    val ime: String,
    val prezime: String,
    val godineIskustva: Int,
    val oznakaZemlje: String,
    jezici: List<String>
) : Osoba {

    val jezici: List<String>

    init {
        if (ime.isBlank()) {
            throw IllegalArgumentException("Ime ne može biti prazno")
        }

        if (prezime.isBlank()) {
            throw IllegalArgumentException("Prezime ne može biti prazno")
        }

        if (godineIskustva < 0) {
            throw IllegalArgumentException("Iskustvo ne može biti negativno")
        }

        if (oznakaZemlje.isBlank()) {
            throw IllegalArgumentException("Oznaka zemlje ne može biti prazna")
        }

        if (jezici.isEmpty()) {
            throw IllegalArgumentException("Lista jezika ne može biti prazna")
        }

        val temp = mutableListOf<String>()
        for (j in jezici) {
            if (j.isNotBlank()) {
                temp.add(j.lowercase())
            }
        }

        if (temp.isEmpty()) {
            throw IllegalArgumentException("Nakon obrade lista jezika ne smije biti prazna")
        }

        this.jezici = temp.toList()
    }

    override fun punIdentitet(): String {
        return "$ime $prezime"
    }

    override fun zemljaPorijekla(): String {
        return oznakaZemlje
    }
}

class BackendDeveloper(
    ime: String,
    prezime: String,
    godineIskustva: Int,
    oznakaZemlje: String,
    jezici: List<String>,
    val backendFramework: String
) : Programer(ime, prezime, godineIskustva, oznakaZemlje, jezici)

class FrontendDeveloper(
    ime: String,
    prezime: String,
    godineIskustva: Int,
    oznakaZemlje: String,
    jezici: List<String>,
    val frontendFramework: String
) : Programer(ime, prezime, godineIskustva, oznakaZemlje, jezici)

fun brojProgrameraPoJezikuManual(programeri: List<Programer>): Map<String, Int> {
    val rez = mutableMapOf<String, Int>()
    for (p in programeri) {
        for (j in p.jezici) {
            val key = j.lowercase()
            rez[key] = (rez[key] ?: 0) + 1
        }
    }
    return rez
}

fun brojProgrameraPoJezikuGroup(programeri: List<Programer>): Map<String, Int> {
    return programeri
        .flatMap { it.jezici.map { jezik -> jezik.lowercase() } }
        .groupingBy { it }
        .eachCount()
}

fun prosjecnoIskustvoPoJezikuManual(programeri: List<Programer>): Map<String, Double> {
    val suma = mutableMapOf<String, Int>()
    val broj = mutableMapOf<String, Int>()

    for (p in programeri) {
        for (j in p.jezici) {
            val key = j.lowercase()
            suma[key] = (suma[key] ?: 0) + p.godineIskustva
            broj[key] = (broj[key] ?: 0) + 1
        }
    }

    val rez = mutableMapOf<String, Double>()
    for (k in suma.keys) {
        rez[k] = suma[k]!!.toDouble() / broj[k]!!
    }
    return rez
}

fun prosjecnoIskustvoPoJezikuGroup(programeri: List<Programer>): Map<String, Double> {
    return programeri
        .flatMap { p -> p.jezici.map { it.lowercase() to p.godineIskustva } }
        .groupBy({ it.first }, { it.second })
        .mapValues { (_, godine) -> godine.average() }
}

fun filtrirajPoFrameworku(programeri: List<Programer>, framework: String): List<Programer> {
    return programeri.filter { p ->
        when (p) {
            is BackendDeveloper -> p.backendFramework.equals(framework, ignoreCase = true)
            is FrontendDeveloper -> p.frontendFramework.equals(framework, ignoreCase = true)
            else -> false
        }
    }
}
fun precentUsingLanguage(programeri: List<Programer>,):Map<String,Double>{

    val broj = mutableMapOf<String, Int>()
    val brojProgramera =mutableMapOf<String, Int>()
    for(p in programeri) {
        for (j in p.jezici) {
            if (p.jezici.equals("lang"))
            val kljuc = j.lowercase()
            broj[kljuc] = (broj[kljuc] ?: 0) + 1
        }
        for(m in p.ime){
            val k=m.lowercase()
            brojProgramera[k] = (brojProgramera[k] ?: 0) + 1

        }
    }
    val rezultat = mutableMapOf<String, Double>()

    for (i in brojProgramera.keys){

        rezultat[i]=(brojProgramera[i]?.toDouble() / broj[i]?.toDouble()!!) *100
    }

    return rezultat


}
fun ispisProgramera(programeri: List<Programer>) {
    for (p in programeri) {
        when (p) {
            is BackendDeveloper -> println("${p.punIdentitet()} — Backend developer — jezici: ${p.jezici.joinToString(", ")} — framework: ${p.backendFramework}")
            is FrontendDeveloper -> println("${p.punIdentitet()} — Frontend developer — jezici: ${p.jezici.joinToString(", ")} — framework: ${p.frontendFramework}")
            else -> println("${p.punIdentitet()} — Programer — jezici: ${p.jezici.joinToString(", ")}")
        }
    }
}

fun main() {
    val programeri = listOf(
        BackendDeveloper("Adnan", "Kurtic", 4, "BA", listOf("Kotlin", "Lang"), "Spring Boot"),
        FrontendDeveloper("Emina", "Smajic", 3, "DE", listOf("JavaScript", "Kotlin"), "React"),
        BackendDeveloper("Maja", "Stankovic", 5, "SRB", listOf("Java", "Kotlin"), "Ktor"),
        FrontendDeveloper("Luka", "Markovic", 2, "HR", listOf("TypeScript", "Lang"), "Vue.js"),
        BackendDeveloper("Nina", "Ibrahimovic", 6, "BA", listOf("Java", "Scala"), "Spring Boot")
    )

    println("Prikaz svih programera:")
    ispisProgramera(programeri)

    println("Procenat programera koji koriste lang")
   precentUsingLanguage(programeri)

    check(programeri.all { it.jezici.isNotEmpty() })

    check(programeri.filterIsInstance<BackendDeveloper>().all { it.backendFramework.isNotBlank() })

    check(programeri.filterIsInstance<FrontendDeveloper>().all { it.frontendFramework.isNotBlank() })

    println("\nBroj programera po jeziku (manual):")
    println(brojProgrameraPoJezikuManual(programeri))

    println("\nBroj programera po jeziku (groupingBy):")
    println(brojProgrameraPoJezikuGroup(programeri))

    println("\nProsječno iskustvo po jeziku (manual):")
    println(prosjecnoIskustvoPoJezikuManual(programeri))

    println("\nProsječno iskustvo po jeziku (groupingBy):")
    println(prosjecnoIskustvoPoJezikuGroup(programeri))

    println("\nFiltrirani programeri po frameworku 'Spring Boot':")
    ispisProgramera(filtrirajPoFrameworku(programeri, "Spring Boot"))
}

