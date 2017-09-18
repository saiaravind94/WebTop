# WebTop
*	Webtop application deals with order creation and provisioning of order in webtop and SIMON application.
*	This is the selenium project and it uses TestNG with Maven framework. 
*	Also we have SOA updator script to update the SOA file for each test case and you can find the SOA scripts in src\test\resources\SOAFileUpdatorScripts\ location in the project workspace. 

# Framework Features:
*	TesTNG and Maven based project that supports parallel execution.
*	It supports package execution.
*	Multibrowser/MultiENV supported
*	Logger statements are implemented to troubleshoot the issues with INFO, FAIL, WARN and DEBUG levels.
*	Customized HTML and Summary reports for the test case level and suite level
*	Page Object Model / Locators for each page classes
*	ALM integration with REST API
*	Grid execution supported
*	Dump file will be created for each test case and you can add/retrieve the data to/from the dump file and also it will keep track of the test method status. 
*	Interactive mode supported<br>
 For More Information on Framework Features - Refer to the link below
Read the Wiki page about the framework in the following link [Selenium template Wiki](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki) to understand the framework in details.

# How to download the project?
*	Use 'git clone https://github.comcast.com/quality-driven-delivery/Webtop' in 'Git Bash/Git Shell' Command tool in your Local computer.
*	Import as a maven project and do the Maven update.
	Note: Make sure you have Java and Maven installed.

# VDIs:
The Following VDI's are used for test automation<br>
TACGWC-WBTP01 - 10

# WebTop BVT Job URL:
[Line Based Order](http://masjenci-wc-a1p.cable.comcast.com:8080/view/WEBTOP%20E2E/job/BVT_WEBTOP_LineBasedOrder/) - Executed on TACGWC-WBTP01<br>
[Seat Based Order](http://masjenci-wc-a1p.cable.comcast.com:8080/view/WEBTOP%20E2E/job/BVT_WEBTOP_SeatBasedOrder/) - Executed on TACGWC-WBTP02<br>
[WebTop PROD Job URL](http://masjenci-wc-a1p.cable.comcast.com:8080/view/WEBTOP_PROD_BSDQE/job/ProdTest_BSDQE_LB_STD/) - Executed on TACGWC-WBTP01

# ALM Details:
	https://almcomcast.saas.hpe.com/qcbin/start_a.jsp?
	DOMAIN: CET_SIT
	PROJECT:CROSS_GRP_TEST
	Release Folder Location: \\Root\Active\BVE_WEBTOP\BVE_<Release Name>\Regression

# Do's and Don'ts

* Make sure to push your tested code to remote github everyday. <br>
Team should follow the Do's and Don'ts listed [here](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki/Do's-and-Don'ts) 
	
# Template Test and Page Methods
[Template Test Methods](https://github.comcast.com/quality-driven-delivery/Selenium_template/blob/master/src/test/java/com/comcast/template/TemplateTest1.java)<br>
[Minimal Viable Test Method](https://github.comcast.com/quality-driven-delivery/Selenium_template/blob/master/src/test/java/com/comcast/template/MinimalViableTest.java)<br>
[Template Page Methods](https://github.comcast.com/quality-driven-delivery/Selenium_template/blob/master/src/main/java/com/comcast/pages/TemplatePage.java)<br>



# References
[WebTop Functional Documents](\\172.28.85.164\cdvtestteam$\BVE_PROJECT)<br>
[Core Selenium Framework](https://github.comcast.com/quality-driven-delivery/BSA_Selenium_Core)<br>
[Selenium Framework Wiki](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki)<br>
[BSD COE Slack Channel](https://cim.slack.com/messages/bsd_qe_coe)<br>
[Selenium Template Project](https://github.comcast.com/quality-driven-delivery/Selenium_template)<br>
[Recommended Page Methods](https://github.comcast.com/quality-driven-delivery/Selenium_template/wiki/Recommended-Page-Methods)

