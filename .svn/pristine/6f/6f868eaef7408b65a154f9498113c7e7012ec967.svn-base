#include <Excel.au3>
#include <File.au3>

$one = @UserProfileDir
$three = "\Downloads\iPulse_PRQ_SearchIssuesResult_AutoIt.xlsx"

_Log("")
_Log("")
_Log("")
_Log("************* Nsf-Online Bs06 Case6 script start")
WinWait("[CLASS:XLMAIN]","",15)
If Not WinExists("[CLASS:XLMAIN]","")Then
MsgBox(0, 'Case 6 -- AutoIt Scripts FAIL, Getting error: - ', '   Excel is not getting open after 15 Sec wait.', 10)
_Log("FAILED == Excel is not getting open after 25 Sec wait")
Exit
EndIf
_Log("Excel successfully opened.")
Sleep(1500)
send("^a")
Sleep(1000)
send("^c")
_Log("Excel data copy done")


; ***************************************************************************** 2nd Excel Start
Local $sFilePath = $one & $three ;This file should already exist
If Not FileExists($sFilePath) Then
MsgBox(0, 'Case 6 -- AutoIt Scripts FAIL, Getting error: - ', $sFilePath & '   Excel Does NOT exists', 15)
_Log("FAILED == "& $sFilePath & "   Excel Does NOT exists")
Exit
EndIf

Local $oExcel = _ExcelBookOpen($sFilePath)
Sleep(1000)
_Log("SearchIssueResult_AutoIt.xlsx --- Excel successfully opened.")


Send("^v")
Sleep(1000)
_Log("Excel data paste done")

_ExcelBookClose($oExcel)
_Log("SearchIssueResult_AutoIt.xlsx --- Excel close and save done")
Sleep(2000)


WinWait("[CLASS:XLMAIN]","",5)
If Not WinExists("[CLASS:XLMAIN]","")Then
MsgBox(0, 'Case 6 -- AutoIt Scripts FAIL, Getting error: - ', '   stdSearch Excel is not exists Toclose.', 10)
_Log("FAILED == stdSearch Excel is not exists Toclose.")
Exit
EndIf
WinClose("[CLASS:XLMAIN]","")
;WinWait("Microsoft Excel","",5)
;If Not WinExists("Microsoft Excel","")Then
;MsgBox(0, 'Case 6 -- AutoIt Scripts FAIL, Getting error: - ', '  stdSearch Excel Yes/No button dialog box is not getting open after 15 Sec wait.', 10)
;_Log("FAILED == stdSearch Excel  Yes/No button dialog box is not getting open after 10 Sec wait")
;Exit
;EndIf
;_Log("stdSearch Excel YES/No dialog box opened.")
;Sleep(1500)
;send("!n")
;_Log("Click done on stdSearch Excel dialog box No button.")
_Log("stdSearch.xlsx --- Excel close done")
_Log("************* Successfully Run Script Nsf-Online Bs06 Case6.")




Func _Log($sLogMsg)
_FileWriteLog(@ScriptDir & "\AutoIt_Log.log",$sLogMsg)
EndFunc