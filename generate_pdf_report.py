import os
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time

def generate_pdf_report():
    # Chrome options
    chrome_options = Options()
    chrome_options.add_argument('--headless')
    chrome_options.add_argument('--disable-gpu')
    chrome_options.add_argument('--no-sandbox')
    chrome_options.add_argument('--disable-dev-shm-usage')
    chrome_options.add_argument('--window-size=1920,1080')
    
    # PDF printing options
    chrome_options.add_argument('--print-to-pdf=/Users/yusuf/Desktop/Turnacom/allure_report.pdf')
    
    # Initialize driver
    driver = webdriver.Chrome(options=chrome_options)
    
    try:
        # Load the local HTML file
        report_path = os.path.join(os.getcwd(), 'target', 'allure-report', 'index.html')
        driver.get('file://' + report_path)
        
        # Wait for the page to load
        time.sleep(5)
        
        # Take a screenshot
        driver.save_screenshot('allure_report.png')
        
    finally:
        driver.quit()

if __name__ == '__main__':
    generate_pdf_report()
