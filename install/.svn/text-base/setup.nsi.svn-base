; plucker-install.nsi
;

;--------------------------------
; Definitions
!define JRE_VERSION "1.5"
!define JRE_URL "http://dlc.sun.com/jdk/jre-1_5_0_01-windows-i586-p.exe"

;--------------------------------
; The name of the installer
Name "PluckerInstaller"

; The file to write
OutFile "..\prepare-setup.exe"

XPStyle on

; The default installation directory
InstallDir $PROGRAMFILES\Pluralis\Prepare

;--------------------------------

; Pages

Page directory
Page instfiles

;--------------------------------

; The stuff to install
Section "" ;No components page, name is not important

  ; Ensure installation of JRE
  Call DetectJRE

  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put file there
  File /r lib
  File /r reports
  File /r plugins
  File *.properties
  File *.xml
  File tipoftheday
  File prepare.jar
  File prepare.exe

  CreateShortcut "$DESKTOP\PREPare.lnk" "$INSTDIR\prepare.exe" "" "$INSTDIR\prepare.exe" 0 SW_SHOWNORMAL
  CreateShortcut "$STARTMENU\Programme\PREPare.lnk" "$INSTDIR\prepare.exe" "" "$INSTDIR\prepare.exe" 0 SW_SHOWNORMAL
  
SectionEnd ; end the section

;--------------------------------
; Helpers

Function GetJRE
        MessageBox MB_OK "PREPare uses Java 1.5, it will now \
                         be downloaded and installed"
 
        StrCpy $2 "$TEMP\Java Runtime Environment.exe"
        nsisdl::download /TIMEOUT=30000 ${JRE_URL} $2
        Pop $R0 ;Get the return value
                StrCmp $R0 "success" +3
                MessageBox MB_OK "Download failed: $R0"
                Quit
        ExecWait $2
        Delete $2
FunctionEnd
 
 
Function DetectJRE
  ReadRegStr $2 HKLM "SOFTWARE\JavaSoft\Java Runtime Environment" \
             "CurrentVersion"
  StrCmp $2 ${JRE_VERSION} done
  
  Call GetJRE
  
  done:
FunctionEnd