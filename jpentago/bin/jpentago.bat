set LIB=..\lib
set JME=%LIB%\jme
set JME_JARS=%JME%\jinput.jar;%JME%\jme.jar;%JME%\jme-awt.jar;%JME%\jme-editors.jar;%JME%\jme-effects.jar;%JME%\jme-font.jar;%JME%\jme-gamestates.jar;%JME%\jme-model.jar;%JME%\jme-scene.jar;%JME%\jme-sound.jar;%JME%\jme-terrain.jar;%JME%\jogg-0.0.5.jar;%JME%\jorbis-0.0.12.jar;%JME%\lwjgl.jar;%JME%\lwjgl_applet.jar;%JME%\lwjgl_fmod3.jar;%JME%\lwjgl_test.jar;%JME%\lwjgl_util.jar;%JME%\lwjgl_util_applet.jar;%JME%\native-mac.jar;%JME%\jmetest.jar;%JME%\jmetest-data.jar

set CP=%JME_JARS%;%LIB%\jpentago.jar

::The path to the native libraries needed to ruun
set LIB_PATH=%JME%

echo %CP%

javaw -Djava.library.path=%LIB_PATH% -cp "%CP%" net.jpentago.view.GameBoardView > jpentago_log.out 2>&1