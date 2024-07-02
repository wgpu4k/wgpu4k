plugins {
	`kotlin-dsl`
}


repositories {
	mavenCentral()
	maven( url = "https://jitpack.io" )
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm:2.3.12")
	implementation("io.ktor:ktor-server-netty-jvm:2.3.12")
	implementation("com.github.Osiris-Team:HBrowser:3.0.8")
	implementation("ch.qos.logback:logback-classic:1.5.6")
	implementation("com.microsoft.playwright:playwright:1.41.0")
	implementation(libs.korge.foundation)
	implementation(libs.korge.core)
	implementation(libs.coroutines)
}
