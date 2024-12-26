#!/bin/bash
arquivo=$1.zip

version=3.4.1
java=21
language=${2:-"kotlin"}

echo "Criando projeto $language Spring Boot '$arquivo'..."

curl https://start.spring.io/starter.zip -d dependencies=web \
    -d javaVersion=$java -d type=gradle-project -d language=$language \
    -d applicationName=Application -d bootVersion=$version -d artifactId=$1 \
    -d version=0.0.0 -o $arquivo 

# Descompacta o projeto
echo "Descompactando o arquivo '$arquivo'..."
mkdir $1 && unzip -q "$arquivo" -d $1 && chmod 777 ./$1

echo "Criando módulos básicos ..."
moduleRest="./$1/$1-rest-api/src/main/$language/"
moduleRestResource="./$1/$1-rest-api/src/main/resources/"
moduleRestTest="./$1/$1-rest-api/src/test/$language/"

moduleAsync="./$1/$1-async-processor/src/main/$language/"
moduleAsyncResource="./$1/$1-async-processor/src/main/resources/"
moduleAsyncTest="./$1/$1-async-processor/src/test/$language/"

moduleCore="./$1/$1-core/src/main/$language/"
moduleCoreResource="./$1/$1-core/src/main/resources/"
moduleCoreTest="./$1/$1-core/src/test/$language/"

mkdir -pv $moduleRest 
mkdir -pv $moduleRestResource
mkdir -pv $moduleRestTest
mkdir -pv $moduleAsync
mkdir -pv $moduleAsyncResource
mkdir -pv $moduleAsyncTest
mkdir -pv $moduleCore
mkdir -pv $moduleCoreResource
mkdir -pv $moduleCoreTest

rm -rf ./$1/src ./$1/settings.gradle ./$1/build.gradle

touch "./$1/$1-rest-api/build.gradle.kts"
touch "./$1/$1-rest-api/src/main/resources/application.properties"
touch "./$1/$1-async-processor/build.gradle.kts"
touch "./$1/$1-async-processor/src/main/resources/application.properties"
touch "./$1/$1-core/build.gradle.kts"
touch "./$1/build.gradle.kts"
touch "./$1/settings.gradle.kts"

SOURCE_FILE_ASYNC="./templates/template.async.build.gradle.kts"
SOURCE_FILE_RESTAPI="./templates/template.restapi.build.gradle.kts"
SOURCE_FILE_CORE="./templates/template.core.build.gradle.kts"
SOURCE_FILE="./templates/template.build.gradle.kts"

TARGET_FILE_RESTAPI="./$1/$1-rest-api/build.gradle.kts"
TARGET_FILE_ASYNC="./$1/$1-async-processor/build.gradle.kts"
TARGET_FILE_CORE="./$1/$1-core/build.gradle.kts"
TARGET_FILE="./$1/build.gradle.kts"

cat "$SOURCE_FILE_ASYNC" >> "$TARGET_FILE_ASYNC"
cat "$SOURCE_FILE_RESTAPI" >> "$TARGET_FILE_RESTAPI"
cat "$SOURCE_FILE_CORE" >> "$TARGET_FILE_CORE"
cat "$SOURCE_FILE" >> "$TARGET_FILE"

echo "rootProject.name=\"$1\"" >> "./$1/settings.gradle.kts"
echo "include(\"$1-rest-api\")" >> "./$1/settings.gradle.kts"
echo "include(\"$1-async-processor\")" >> "./$1/settings.gradle.kts"
echo "include(\"$1-core\")" >> "./$1/settings.gradle.kts"

echo "spring.application.name=$1-rest-api" >> "./$1/$1-rest-api/src/main/resources/application.properties"
echo "spring.application.name=$1-async-processor" >> "./$1/$1-async-processor/src/main/resources/application.properties"

echo "Projeto criado ..."