$projectRoot = 'c:\Users\paulo.cesar.carvalho\Documents\ProjetoBase'
Set-Location $projectRoot

# Encontrar JARs do Apache POI
$poiJar = Get-ChildItem -Path "$env:USERPROFILE\.m2\repository\org\apache\poi\poi\5.2.3\" -Filter "*.jar" | Select-Object -First 1
$poiOoxmlJar = Get-ChildItem -Path "$env:USERPROFILE\.m2\repository\org\apache\poi\poi-ooxml\5.2.3\" -Filter "*.jar" | Select-Object -First 1
$poiOoxmlLiteJar = Get-ChildItem -Path "$env:USERPROFILE\.m2\repository\org\apache\poi\poi-ooxml-lite\5.2.3\" -Filter "*.jar" | Select-Object -First 1

$jars = @(
    "target\classes",
    $poiJar.FullName,
    $poiOoxmlJar.FullName,
    $poiOoxmlLiteJar.FullName
)

$classPath = $jars -join ';'
Write-Host "ClassPath: $classPath"
Write-Host "Executing ExcelTemplateGenerator..."
java -cp "$classPath" com.projeto.seguros.utils.ExcelTemplateGenerator
