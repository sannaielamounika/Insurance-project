from selenium import webdriver

def test_application():
    driver = webdriver.Chrome()
    driver.get("http://<EC2-IP>:8080")
    assert "InsureMe" in driver.title
    driver.quit()

test_application()
