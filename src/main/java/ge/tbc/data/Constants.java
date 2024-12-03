package ge.tbc.data;

public class Constants {
    // Base URL and navigation links
    public static final String BASE_URL = "https://swoop.ge";
    public static final String REST_LINK = "დასვენება";

    // Filter and sort options
    public static final String MOUNTAIN_RESORTS = "//h5[text()='მთის კურორტები']";
    public static final String PAYMENT_TYPE_RADIO = "//input[@id='radio-გადახდის ტიპი-1']";
    public static final String FULL_PAYMENT_BUTTON = "//p[text()='სრული გადახდა']";
    public static final String SORT_BY_RELEVANCE = "//p[text()='შესაბამისობით']";
    public static final String SORT_BY_ASCENDING_PRICE = "//p[text()='ფასით ზრდადი']";
    public static final String SORT_BY_DESCENDING_PRICE = "//p[text()='ფასით კლებადი']";


    // Price list and pagination
    public static final String PRICE_XPATH = "//h4[contains(@class ,'text-2md')]";
    public static final String NEXT_ARROW_XPATH = "//img[@alt='right arrow']/..";

    //
   public static final String CATEGORIES_XPATH = "//p[text()='კატეგორიები' and contains(@class, 'text-md')]";
    public static final String SPORT_BTN = "//h4[text() = 'სპორტი']";
    public static final String CARTING_BTN ="//h4[text() = 'კარტინგი']";
    public static final String SWOOPLOGO_XPATH ="//a[contains(@class, 'py-2')]";
    public static final String KINO = "კინო";
    public static final String ALL_MOVIEA =".w-full.group";

    public static final String VACANT_PLACE = "//div[@class = 'cursor-pointer ']";
    public static final String REGISTRATION = ".black-hover";
    public static final String EASTPOINTBTN_XPATH ="//h3[text()='კავეა ისთ ფოინთი' and contains(@class, 'text-2md')]";

    public static final String INPUTS_XPATH ="//input[@placeholder='₾0']";
    public static final String ENTER_BUTTON = "//button[@data-testid='secondary-button']";
    public static final String EXPECTEDURL = "https://swoop.ge/category/2058/sporti/kartingi/";
    public static final String EXPECTEDURL2 = "https://swoop.ge/";

    public static final String VETANXMEBI_XPATH = "//p[text()='ვეთანხმები']";
    public static final String MOVIE_TAGNAME = "h1";
    public static final String CAVEAEASTPOINT_WRAPPER = "//div[h3[contains(text(), 'კავეა ისთ ფოინთი')]]";
    public static final String DATA_TIME_XPATH = "//div[contains(@class, 'items-end')]/p";
    public static final String OPTIONS_XPATH =  "following-sibling::div//div[contains(@class, 'cursor-pointer')]";
    public static final String CHART_COLOR =  "//p[text()='თავისუფალი']/preceding-sibling::div[contains(@class, 'rounded-full')]";
    public static final String FREESET_SELECTOR = ".cursor-pointer";
}

