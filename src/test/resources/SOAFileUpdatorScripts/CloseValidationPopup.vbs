Set oShell = CreateObject("shell.application")
oShell.MinimizeAll
Set oShell = Nothing
Set oShell = CreateObject("WScript.Shell")
Do While(true)
	If(oShell.AppActivate("Order")) Or (oShell.AppActivate("Microsoft")) Then
		WScript.Sleep 1000
		oShell.SendKeys "{ESCAPE}"
		oShell.SendKeys "{ENTER}"
	End If
Loop

