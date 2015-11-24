copyright © 2015, Felföldi Balázs. See LICENSE.txt for details.

# What You Stroke Is Not What You Get

What You Stroke Is Not What You Get (WYSINWYG) is a component segmented type writing program. There are 4 mayor components of the typing process.

Device
------
The actual physical device on which you press and release the keys. Every key press or release generates a device event containing information about the actual key. This event is pushed to the selected evaluator.

Evaluator
---------
Evaluators process the device events sent by the selected device. Every key has a code which is connected with a smybol that is useful to the evaluator. In case of using the NKRO Keyboard with the embedded steno evaluator in a win32 context, pressing the keyboard C key generates a virtual key code 67 (0x43). The steno evaluators symbol A is connected with the code 67. After the key stroke is finished the evaluated values are pushed to the selected translator.

Translator
----------
Translators process the received evaluator symbols and find the correct dictionary values for it, which we want to actually print on the screen. This can be one or multiple characters depending on the dictionary value.

Printer
-------
Sends simulated key presses and commands to the underlying operating system.

Project Resources
-------
The project uses the following resources:

Name: JNA
Version: 4.1.0
Link: https://github.com/java-native-access/jna

Name: JSON.simple
Version: 1.1.1
Link: https://code.google.com/p/json-simple/

Name: JNativeHook
Version: 2.0.1
Link: https://github.com/kwhat/jnativehook

RXTX binary builds provided as a courtesy of Mfizz Inc. (http://mfizz.com/).
Please see http://mfizz.com/oss/rxtx-for-java for more information.