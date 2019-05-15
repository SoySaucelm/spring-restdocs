package com.cf.demo.test;

import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author SoySauce
 * @date 2019/5/13
 */
//@RunWith(SpringRunner.class)
//@WebMvcTest(HomeController.class)
//@AutoConfigureRestDocs(outputDir = "target/snippets")
////@SpringBootTest
public class DocTest {

    //    @Autowired
    private MockMvc mockMvc;
    //    @Autowired
    private WebApplicationContext context;

    //    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello World")))
//                .andDo(document(""));
//    }
//    @Before
//    public void setUp() {
////        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/snippets2");

    //    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }


    //    @Test
    public void GetAllUserTest() throws Exception {
//        this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("index", org.springframework.restdocs.hypermedia.HypermediaDocumentation.links(
//                        linkWithRel("alpha").description("Link to the alpha resource"),
//                        linkWithRel("bravo").description("Link to the bravo resource"))));

        this.mockMvc
                .perform(
                        post("/api/v1.0.1/list")
                                .header("access_token", "312321")
                                .header("user_uuid", "3213123")
                                .param("age", "1")
                )
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("1.1获取所有用户接口",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("access_token").description("Basic auth credentials"),
                                headerWithName("user_uuid").description("User Uuid Key")
                        ),
                        requestParameters(
                                parameterWithName("age").description("年龄")
                        ),
                        responseFields(
                                fieldWithPath("code").description("0.失败 1.成功").type(JsonFieldType.NUMBER),
                                fieldWithPath("message").description("提示消息"),
                                fieldWithPath("userList[].id").description("用户id"),
                                fieldWithPath("userList[].name").description("姓名"),
                                fieldWithPath("userList[].age").description("用户密码"),
                                fieldWithPath("userList[].lastActiveTime").description("最近活动时间"),
                                fieldWithPath("userList[].userName").description("用户名"),
                                fieldWithPath("userList[].password").description("用户密码"),
                                fieldWithPath("userList[].userUuid").description("用户UUId")
                        )
                        )
                );
    }

    String dir = System.getProperty("user.dir");
    String path = dir + "\\src\\docs\\markdown\\all\\api.adoc";

    //    @Test
    public void adocBuild() throws IOException {
        String appDir = System.getProperty("user.dir");
        String adocPath = appDir + "\\src\\docs\\api\\asciidocs\\apiList.adoc";
        StringBuilder content = new StringBuilder();
        content.append("include::" + appDir + "\\src\\docs\\api\\base\\preview.adoc[]" + "\n\n");

        Files.list(Paths.get(appDir + "\\target\\snippets")).forEach(f -> {
            String apiName = f.getFileName().toString();
            content.append("=== " + apiName + "\n\n");
            fileAppend(content, f + "\\request-headers.adoc", "request-headers 类型说明");
            fileAppend(content, f + "\\http-request.adoc", "http-request");
            fileAppend(content, f + "\\request-fields.adoc", "request-fields 类型说明");
            fileAppend(content, f + "\\request-parameters.adoc", "request-parameters类型说明");
            fileAppend(content, f + "\\request-body.adoc", "request-body 类型说明");
            fileAppend(content, f + "\\http-response.adoc", "http-response");
            fileAppend(content, f + "\\response-fields.adoc", "response-fields 类型说明");
        });
        Files.write(Paths.get(adocPath), content.toString().getBytes(), StandardOpenOption.CREATE);
//        FileCopyUtils.copy(content.toString(), new FileWriter(adocPath));
    }

    public static void main(String[] args) {
        try {
            new DocTest().adocBuild();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void fileAppend(StringBuilder stringBuilder, String path, String title) {
        if (Files.exists(Paths.get(path))) {
            stringBuilder.append("." + title + "\n\n");
            stringBuilder.append("include::" + path + "[]" + "\n\n");
        }
    }


}
