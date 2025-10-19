@echo off
SETLOCAL

REM --- Configuration ---
REM Il est recommandé d'utiliser la syntaxe SET "NOM_VARIABLE=Valeur" pour plus de robustesse,
REM mais SET NOM_VARIABLE="Valeur" fonctionne aussi. La clé est la cohérence.
REM Les valeurs ici sont des chaînes sans guillemets internes après l'affectation.
SET APP_NAME="MonApplication"
SET APP_VERSION_MAVEN="0.0.1-SNAPSHOT"
SET APP_VERSION_JPACKAGE="0.0.1.0"
SET VENDOR="Votre Entreprise"
SET COPYRIGHT="Copyright 2025 KAAKARA"
SET DESCRIPTION="Application locale pour les utilisateurs non-techniques"
SET ICON_PATH="src\main\resources\icon.ico"
SET MAIN_CLASS="org.aemudapi.Application"
SET OUTPUT_DIR="target\installer"
SET INPUT_FOLDER="target"

REM --- NOUVEAUX REPERTOIRES POUR L'APPLICATION PRÉPARÉE ET LES DÉPENDANCES ---
REM Utilisation de SET "NOM_VARIABLE=Valeur" pour une meilleure gestion des espaces potentiels dans les noms eux-mêmes.
SET "APP_PREPARED_DIR=%INPUT_FOLDER%\jpackage-app"
SET "LIBS_DIR=%INPUT_FOLDER%\dependency-jars"
SET "CLASSES_DIR=%APP_PREPARED_DIR%\classes"

REM --- CHEMIN VERS JPACKAGE DIRECTEMENT ---
SET "JPACKAGE_EXE=C:\Program Files\Java\jdk-17\bin\jpackage.exe"

REM --- Vérification de JAVA_HOME ---
IF NOT DEFINED JAVA_HOME (
    echo Erreur : JAVA_HOME n'est pas défini. Veuillez le configurer pour pointer vers votre JDK.
    pause
    GOTO :EOF
)
REM --- Vérification de l'existence de jpackage.exe ---
IF NOT EXIST "%JPACKAGE_EXE%" (
    echo Erreur : jpackage.exe non trouvé à "%JPACKAGE_EXE%".
    echo Veuillez vérifier le chemin vers votre JDK et la présence de jpackage.
    pause
    GOTO :EOF
)

REM --- Exécution de Maven pour construire les JARs et copier les dépendances ---
echo.
echo "Building Spring Boot JARs and copying dependencies with Maven..."
call mvn clean package

IF %ERRORLEVEL% NEQ 0 (
    echo "Maven build failed. Exiting."
    GOTO :EOF
)

REM --- Préparer les fichiers pour jpackage ---
echo.
echo "Preparing application files for jpackage..."

REM Suppression des anciens répertoires (avec guillemets pour robustesse)
IF EXIST "%APP_PREPARED_DIR%" (
    RMDIR /S /Q "%APP_PREPARED_DIR%"
    IF %ERRORLEVEL% NEQ 0 (
        echo "Erreur : Impossible de supprimer le dossier %APP_PREPARED_DIR%."
        echo "Veuillez le supprimer manuellement et relancer."
        pause
        GOTO :EOF
    )
)
IF EXIST "%LIBS_DIR%" (
    RMDIR /S /Q "%LIBS_DIR%"
    IF %ERRORLEVEL% NEQ 0 (
        echo "Erreur : Impossible de supprimer le dossier des dépendances %LIBS_DIR%."
        echo "Veuillez le supprimer manuellement et relancer."
        pause
        GOTO :EOF
    )
)

REM Création des nouveaux répertoires (avec guillemets pour robustesse)
MKDIR "%LIBS_DIR%"
IF %ERRORLEVEL% NEQ 0 (
    echo "Erreur : Impossible de créer le dossier %LIBS_DIR%."
    pause
    GOTO :EOF
)
MKDIR "%APP_PREPARED_DIR%"
IF %ERRORLEVEL% NEQ 0 (
    echo "Erreur : Impossible de créer le dossier %APP_PREPARED_DIR%."
    pause
    GOTO :EOF
)
MKDIR "%CLASSES_DIR%"
IF %ERRORLEVEL% NEQ 0 (
    echo "Erreur : Impossible de créer le dossier %CLASSES_DIR%."
    pause
    GOTO :EOF
)

REM Déterminer le chemin du JAR SIMPLE
SET "SIMPLE_APP_JAR=%INPUT_FOLDER%\aemud-api-%APP_VERSION_MAVEN%.jar"
SET "SIMPLE_APP_JAR_FILENAME=aemud-api-%APP_VERSION_MAVEN%.jar" REM Nom de fichier seul pour --main-jar

REM Vérification de l'existence du JAR (avec guillemets)
IF NOT EXIST "%SIMPLE_APP_JAR%" (
    echo "Erreur : Le JAR simple de l'application n'a pas été trouvé à %SIMPLE_APP_JAR%."
    echo "Veuillez vérifier le nom du JAR et votre configuration Maven."
    pause
    GOTO :EOF
)
REM Maven devrait créer LIBS_DIR via la configuration du plugin (par exemple maven-dependency-plugin)
REM Cette vérification est donc pour s'assurer que le plugin a bien fonctionné si LIBS_DIR est censé être peuplé par Maven.
REM Si vous copiez les dépendances manuellement ou autrement, ajustez la logique.
REM Note: Le script actuel exécute 'mvn clean package', qui devrait populer 'target/dependency-jars'
REM si le pom.xml est configuré pour cela (e.g. avec maven-dependency-plugin:copy-dependencies).

REM Copier le JAR SIMPLE de votre application vers le dossier des classes préparées pour jpackage (avec guillemets)
COPY "%SIMPLE_APP_JAR%" "%CLASSES_DIR%\"
IF %ERRORLEVEL% NEQ 0 (
    echo "Erreur : Impossible de copier %SIMPLE_APP_JAR% vers %CLASSES_DIR%."
    pause
    GOTO :EOF
)

REM --- Création du dossier de sortie si nécessaire (avec guillemets) ---
IF NOT EXIST "%OUTPUT_DIR%" (
    MKDIR "%OUTPUT_DIR%"
    IF %ERRORLEVEL% NEQ 0 (
        echo "Erreur : Impossible de créer le dossier de sortie %OUTPUT_DIR%."
        pause
        GOTO :EOF
    )
)

REM --- Exécution de jpackage ---
echo.
echo "Running jpackage..."

REM DEBUG : AFFICHE LA COMMANDE JPACKAGE AVANT L'EXÉCUTION (décommenter si besoin)
REM echo "%JPACKAGE_EXE%" ^
REM     --name "%APP_NAME%" ^
REM     --app-version %APP_VERSION_JPACKAGE% ^
REM     --vendor "%VENDOR%" ^
REM     --copyright "%COPYRIGHT%" ^
REM     --description "%DESCRIPTION%" ^
REM     --icon "%ICON_PATH%" ^
REM     --dest "%OUTPUT_DIR%" ^
REM     --win-shortcut ^
REM     --win-menu ^
REM     --win-console ^
REM     --verbose ^
REM     --input "%CLASSES_DIR%" ^
REM     --input "%LIBS_DIR%" ^
REM     --main-class "%MAIN_CLASS%" ^
REM     --main-jar "%SIMPLE_APP_JAR_FILENAME%"
REM echo.

REM Les arguments de jpackage qui peuvent contenir des espaces (comme VENDOR, DESCRIPTION)
REM ou qui sont des chemins doivent être entourés de guillemets.
"%JPACKAGE_EXE%" ^
    --name "%APP_NAME%" ^
    --app-version %APP_VERSION_JPACKAGE% ^
    --vendor "%VENDOR%" ^
    --copyright "%COPYRIGHT%" ^
    --description "%DESCRIPTION%" ^
    --icon "%ICON_PATH%" ^
    --dest "%OUTPUT_DIR%" ^
    --win-shortcut ^
    --win-menu ^
    --win-console ^
    --verbose ^
    --input "%CLASSES_DIR%" ^
    --input "%LIBS_DIR%" ^
    --main-class "%MAIN_CLASS%" ^
    --main-jar "%SIMPLE_APP_JAR_FILENAME%"

IF %ERRORLEVEL% NEQ 0 (
    echo "jpackage failed."
) ELSE (
    echo "jpackage succeeded. Check %OUTPUT_DIR%"
)

pause
ENDLOCAL