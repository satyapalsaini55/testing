#include <File.au3>
#include <AutoItConstants.au3>

$title="Windows Security"
_FileCreate (@ScriptDir & "\AutoIt_Log.log")
_Log(" ")
_Log(" ")



For $i=1 to 12 step + 1
	if WinExists($title,"")	Then
			$fulltitle = WinGetTitle($title)
			WinActivate ($fulltitle, "")
			 WinSetOnTop($fulltitle, "", $WINDOWS_ONTOP)
			_Log("PASSED == authentication Popup is coming after "& $i &" seconds wait.")
			Sleep(1000)
			ControlClick($fulltitle, "", "", "left")
			ControlSend($fulltitle, "", "", "testscriptuser{TAB}")
			Sleep(1000)
			_Log("PASSED == User name entered.")
			ControlSend($fulltitle, "", "", "welcome123123{TAB}")
			Sleep(1000)
			_Log("PASSED == Password entered.")
			ControlSend($fulltitle, "", "", "{ENTER}")
			_Log("PASSED == Login button clicked.")
			ExitLoop
	else
			Sleep (1000)
	EndIf
	if $i==12 Then
		_Log("FAILED == authentication Popup is NOT coming after "& $i &" seconds wait.")
		MsgBox(16, 'Automation AutoIt   ---   SSO Login FAIL', '   User Name and password authentication Popup is not coming after 120 seconds wait.', 10)
		EndIf
Next


































Func _Log($sLogMsg)
_FileWriteLog(@ScriptDir & "\AutoIt_Edge_Log.log",$sLogMsg)
EndFunc