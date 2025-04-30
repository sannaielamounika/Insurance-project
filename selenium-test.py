from selenium import webdriver
from selenium.webdriver.chrome.options import Options

def test_application():
    chrome_options = Options()
    # run in headless mode
    chrome_options.add_argument("--headless")
    chrome_options.add_argument("--no-sandbox")
    chrome_options.add_argument("--disable-dev-shm-usage")
    chrome_options.add_argument("window-size=1920,1080")

    # point to the chromium binary
    chrome_options.binary_location = "/usr/bin/chromium"

    # start Chrome
    driver = webdriver.Chrome(options=chrome_options)
    driver.get("http://ec2-52-4-82-198.compute-1.amazonaws.com:8080")

    # Debug: print the actual title to Jenkins logs
    print("Page title is:", driver.title)

    # Assert with helpful error message
    assert "InsureMe" in driver.title, f"Expected 'InsureMe' in title, but got '{driver.title}'"

    driver.quit()

if __name__ == "__main__":
    test_application()

