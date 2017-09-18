Set oShell = CreateObject("WScript.Shell")
Do While(true)
	If(oShell.AppActivate("Order")) Or (oShell.AppActivate("Microsoft")) Then
		WScript.Sleep 1000
		oShell.SendKeys "{ESCAPE}"
	End If
Loop

