package com.Util;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.text.TextBlock;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.util.Rotation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GenerateChartUtil {
    /**
     * 生成柱状图(返回JFreeChart)
     *
     * @param chartTitle      图表标题
     * @param legendNameList  图例名称列表
     * @param xAxisNameList   x轴名称列表
     * @param dataList        数据列表
     * @param theme           主题(null代表默认主题)
     * @param yAxisTitle      y轴标题
     * @param xAxisTitle      x轴标题
     * @param yAxisMinValue   y轴最小值（可以为空）
     * @param yAxisMaxValue   y轴最大值（可以为空）
     * @param legendColorList 图例背景颜色（可以为空）
     * @param barLabelVisible 是否显示柱体标签（可以为空）
     * @param barLabelFormat  柱体标签格式（可以为空）
     * @return
     */
    public static JFreeChart createBarChart(String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle, Double yAxisMinValue
            , Double yAxisMaxValue, List<Color> legendColorList, Boolean barLabelVisible, String barLabelFormat) throws Exception {
        //设置主题，防止中文乱码
        theme = theme == null ? JFreeChartUtil.createChartTheme("") : theme;
        ChartFactory.setChartTheme(theme);
        //创建柱状图
        JFreeChart chart = ChartFactory.createBarChart(chartTitle, xAxisTitle, yAxisTitle
                , JFreeChartUtil.createDefaultCategoryDataset(legendNameList, xAxisNameList, dataList));
        // 设置抗锯齿，防止字体显示不清楚
        chart.setTextAntiAlias(false);
        // 对柱子进行渲染
        JFreeChartUtil.setBarRenderer(chart.getCategoryPlot(), true);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        // 最大换行数
        categoryAxis.setMaximumCategoryLabelLines(10);
        //y轴
        ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
        if (yAxisMinValue != null) {
            valueAxis.setLowerBound(yAxisMinValue);
        }
        if (yAxisMaxValue != null) {
            valueAxis.setUpperBound(yAxisMaxValue);
        }
        CategoryItemRenderer customBarRenderer = plot.getRenderer();
        //显示每个柱的数值
        if (barLabelVisible != null) {
            customBarRenderer.setDefaultItemLabelsVisible(barLabelVisible);
            //柱体数值格式
            if (StrUtil.isNotEmpty(barLabelFormat)) {
                customBarRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator(barLabelFormat, NumberFormat.getInstance()));
            }
        }
        //设置系列柱体背景颜色
        if (CollectionUtil.isNotEmpty(legendColorList)) {
            for (int i = 0; i < legendNameList.size() && i < legendColorList.size(); i++) {
                Color color = legendColorList.get(i);
                if (color == null) {
                    continue;
                }
                customBarRenderer.setSeriesPaint(i, color);
            }
        }
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 标注位于上侧
        chart.getLegend().setPosition(RectangleEdge.TOP);
        return chart;
    }

    /**
     * 生成柱状图(返回outputStream)
     *
     * @param chartTitle      图表标题
     * @param legendNameList  图例名称列表
     * @param xAxisNameList   x轴名称列表
     * @param dataList        数据列表
     * @param theme           主题(null代表默认主题)
     * @param yAxisTitle      y轴标题
     * @param xAxisTitle      x轴标题
     * @param outputStream    输出流
     * @param width           宽度
     * @param height          高度
     * @param yAxisMinValue   y轴最小值（可以为空）
     * @param yAxisMaxValue   y轴最大值（可以为空）
     * @param legendColorList 图例背景颜色（可以为空）
     * @param barLabelVisible 是否显示柱体标签（可以为空）
     * @param barLabelFormat  柱体标签格式（可以为空）
     * @return
     */
    public static void createBarChart(OutputStream outputStream, String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle, int width, int height
            , Double yAxisMinValue, Double yAxisMaxValue, List<Color> legendColorList, Boolean barLabelVisible, String barLabelFormat) throws Exception {
        JFreeChart chart = createBarChart(chartTitle, legendNameList, xAxisNameList, dataList, theme, yAxisTitle, xAxisTitle
                , yAxisMinValue, yAxisMaxValue, legendColorList, barLabelVisible, barLabelFormat);
        try {
            ChartUtils.writeChartAsJPEG(outputStream, 1.0f, chart, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成柱状图(返回byte[])
     *
     * @param chartTitle      图表标题
     * @param legendNameList  图例名称列表
     * @param xAxisNameList   x轴名称列表
     * @param dataList        数据列表
     * @param theme           主题(null代表默认主题)
     * @param yAxisTitle      y轴标题
     * @param xAxisTitle      x轴标题
     * @param width           宽度
     * @param height          高度
     * @param yAxisMinValue   y轴最小值（可以为空）
     * @param yAxisMaxValue   y轴最大值（可以为空）
     * @param legendColorList 图例背景颜色（可以为空）
     * @param barLabelVisible 是否显示柱体标签（可以为空）
     * @param barLabelFormat  柱体标签格式（可以为空）
     * @return
     */
    public static byte[] createBarChart(String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle, int width, int height
            , Double yAxisMinValue, Double yAxisMaxValue, List<Color> legendColorList, Boolean barLabelVisible, String barLabelFormat) throws Exception {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        createBarChart(bas, chartTitle, legendNameList, xAxisNameList, dataList, theme, yAxisTitle, xAxisTitle, width, height
                , yAxisMinValue, yAxisMaxValue, legendColorList, barLabelVisible, barLabelFormat);
        byte[] byteArray = bas.toByteArray();
        return byteArray;
    }

    /**
     * 生成柱堆叠状图(返回JFreeChart)
     *
     * @param chartTitle     图表标题
     * @param legendNameList 图例名称列表
     * @param xAxisNameList  x轴名称列表
     * @param dataList       数据列表
     * @param theme          主题(null代表默认主题)
     * @param yAxisTitle     y轴标题
     * @param xAxisTitle     x轴标题
     * @return
     */
    public static JFreeChart createStackedBarChart(String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle) throws Exception {
        //设置主题，防止中文乱码
        theme = theme == null ? JFreeChartUtil.createChartTheme("") : theme;
        ChartFactory.setChartTheme(theme);
        //创建堆叠柱状图
        JFreeChart chart = ChartFactory.createStackedBarChart(chartTitle, xAxisTitle, yAxisTitle
                , JFreeChartUtil.createDefaultCategoryDataset(legendNameList, xAxisNameList, dataList));
        // 设置抗锯齿，防止字体显示不清楚
        chart.setTextAntiAlias(false);
        // 对柱子进行渲染
        JFreeChartUtil.setBarRenderer(chart.getCategoryPlot(), true);
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 标注位于上侧
        chart.getLegend().setPosition(RectangleEdge.TOP);
        return chart;
    }

    /**
     * 生成堆叠柱状图(返回outputStream)
     *
     * @param chartTitle     图表标题
     * @param legendNameList 图例名称列表
     * @param xAxisNameList  x轴名称列表
     * @param dataList       数据列表
     * @param theme          主题(null代表默认主题)
     * @param yAxisTitle     y轴标题
     * @param xAxisTitle     x轴标题
     * @param outputStream   输出流
     * @param width          宽度
     * @param height         高度
     * @return
     */
    public static void createStackedBarChart(OutputStream outputStream, String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle, int width, int height
    ) throws Exception {
        JFreeChart chart = createStackedBarChart(chartTitle, legendNameList, xAxisNameList, dataList, theme, yAxisTitle, xAxisTitle);
        try {
            ChartUtils.writeChartAsJPEG(outputStream, 1.0f, chart, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成堆叠柱状图(返回byte[])
     *
     * @param chartTitle     图表标题
     * @param legendNameList 图例名称列表
     * @param xAxisNameList  x轴名称列表
     * @param dataList       数据列表
     * @param theme          主题(null代表默认主题)
     * @param yAxisTitle     y轴标题
     * @param xAxisTitle     x轴标题
     * @param width          宽度
     * @param height         高度
     * @return
     */
    public static byte[] createStackedBarChart(String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle, int width, int height) throws Exception {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        createStackedBarChart(bas, chartTitle, legendNameList, xAxisNameList, dataList, theme, yAxisTitle, xAxisTitle, width, height);
        byte[] byteArray = bas.toByteArray();
        return byteArray;
    }

    /**
     * 生成折线图(返回JFreeChart)
     *
     * @param chartTitle     图表标题
     * @param legendNameList 图例名称列表
     * @param xAxisNameList  x轴名称列表
     * @param dataList       数据列表
     * @param theme          主题(null代表默认主题)
     * @param yAxisTitle     y轴标题
     * @param xAxisTitle     x轴标题
     * @return
     */
    public static JFreeChart createLineChart(String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle) throws Exception {
        //设置主题，防止中文乱码
        theme = theme == null ? JFreeChartUtil.createChartTheme("") : theme;
        ChartFactory.setChartTheme(theme);
        //创建折线图
        JFreeChart chart = ChartFactory.createLineChart(chartTitle, xAxisTitle, yAxisTitle
                , JFreeChartUtil.createDefaultCategoryDataset(legendNameList, xAxisNameList, dataList));
        // 设置抗锯齿，防止字体显示不清楚
        chart.setTextAntiAlias(false);

        //获取y轴
        NumberAxis yAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
        //设置y轴的刻度范围和间隔
        yAxis.setRange(0, 8);
        yAxis.setTickUnit(new NumberTickUnit(0.5));
//        // 设置y轴刻度标签的字体大小
//        yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 12));
//        // 设置y轴刻度标签的颜色
//        yAxis.setTickLabelPaint(Color.black);
//        // 设置y轴刻度标签的格式，这里使用默认的格式
//        yAxis.setNumberFormatOverride(NumberFormat.getInstance());
        //获取x轴
        CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
        //设置x轴间隔为50
        xAxis.setCategoryMargin(0.0);
        //设置x轴刻度范围和间隔
        xAxis.setLowerMargin(0.0);
        // 设置x轴刻度标签的角度，这里设置为垂直显示
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        // 设置自定义的CategoryAxis
        xAxis.setAxisLineVisible(false);//设置x轴线不显示
        xAxis.setTickMarkInsideLength(5); // 设置刻度线向内延伸的长度
        xAxis.setTickMarkOutsideLength(0); // 设置刻度线向外延伸的长度
        xAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12)); // 设置刻度标签的字体
        xAxis.setCategoryLabelPositionOffset(5); // 设置刻度标签与刻度线的间距
        xAxis.setTickLabelPaint(Color.BLACK); // 设置刻度标签的颜色
        List<String> whiteList = new ArrayList<>();
        List<String> blackList = new ArrayList<>();
        for(int i = 0; i<xAxisNameList.size(); i++){
            String s = xAxisNameList.get(i);
            if(i%25 ==0){
               blackList.add(s);
            }else {
                whiteList.add(s);
            }
        }
        for (String s : whiteList) {
            xAxis.setTickLabelPaint(s, Color.white);
            xAxis.setTickMarksVisible(false);
        }
        for (String s : blackList) {
            xAxis.setTickLabelPaint(s, Color.black);
        }

        // 对折现进行渲染
        JFreeChartUtil.setLineRender(chart.getCategoryPlot(), true, false);
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 标注位于上侧
        chart.getLegend().setPosition(RectangleEdge.TOP);
        return chart;
    }
    public static class IntervalCategoryAxis extends CategoryAxis {

        private static final long serialVersionUID = -6449312876766886572L;

        private final int stepNum;  // 步数

        public IntervalCategoryAxis(int stepNum) {
            this.stepNum = stepNum;
        }

        public void setDomainAxis(CategoryAxis domainAxis,String[] categories){
            // 设置标签可见
            domainAxis.setTickLabelsVisible(true);
            for(int i = 0; i<categories.length; i++){
                String s = categories[i];
                if(i%5 ==0){
                    domainAxis.setTickLabelPaint(s, Color.black);
                }else{
                    // 设置背景色为白色
                    domainAxis.setTickLabelPaint(s, Color.white);
                }
            }
        }
        /**
         * 重写获取横坐标的方法，根据步数踩点展示，防止横坐标密密麻麻
         */
        @Override
        public List<Tick> refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
            List<Tick> ticks = new ArrayList<>();
            if (dataArea.getHeight() <= 0.0 || dataArea.getWidth() < 0.0) {
                return ticks;
            }
            CategoryPlot plot = (CategoryPlot) getPlot();
            List<?> categories = plot.getCategoriesForAxis(this);
            double max = 0.0;
            if (categories != null) {
                CategoryLabelPosition position = super.getCategoryLabelPositions().getLabelPosition(edge);
                int categoryIndex = 0;
                for (Object o : categories) {
                    Comparable<?> category = (Comparable<?>) o;
                    g2.setFont(getTickLabelFont(category));
                    TextBlock label = new TextBlock();
                    label.addLine(category.toString(), getTickLabelFont(category), getTickLabelPaint(category));
                    if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
                        max = Math.max(max, calculateTextBlockHeight(label, position, g2));
                    } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
                        max = Math.max(max, calculateTextBlockWidth(label, position, g2));
                    }
                    if (categoryIndex % stepNum == 0) {
                        Tick tick = new CategoryTick(category, label, position.getLabelAnchor(), position.getRotationAnchor(), position.getAngle());
                        ticks.add(tick);
                    }
                    categoryIndex = categoryIndex + 1;
                }
            }
            state.setMax(max);
            return ticks;
        }

    }
    /**
     * 生成折线图(返回outputStream)
     *
     * @param chartTitle     图表标题
     * @param legendNameList 图例名称列表
     * @param xAxisNameList  x轴名称列表
     * @param dataList       数据列表
     * @param theme          主题(null代表默认主题)
     * @param yAxisTitle     y轴标题
     * @param xAxisTitle     x轴标题
     * @param outputStream   输出流
     * @param width          宽度
     * @param height         高度
     * @return
     */
    public static void createLineChart(OutputStream outputStream, String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle, int width, int height
    ) throws Exception {
        JFreeChart chart = createLineChart(chartTitle, legendNameList, xAxisNameList, dataList, theme, yAxisTitle, xAxisTitle);
        try {
            ChartUtils.writeChartAsJPEG(outputStream, 1.0f, chart, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成折线图(返回byte[])
     *
     * @param chartTitle     图表标题
     * @param legendNameList 图例名称列表
     * @param xAxisNameList  x轴名称列表
     * @param dataList       数据列表
     * @param theme          主题(null代表默认主题)
     * @param yAxisTitle     y轴标题
     * @param xAxisTitle     x轴标题
     * @param width          宽度
     * @param height         高度
     * @return
     */
    public static byte[] createLineChart(String chartTitle, List<String> legendNameList, List<String> xAxisNameList
            , List<List<Object>> dataList, StandardChartTheme theme, String yAxisTitle, String xAxisTitle, int width, int height) throws Exception {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        createLineChart(bas, chartTitle, legendNameList, xAxisNameList, dataList, theme, yAxisTitle, xAxisTitle, width, height);
        byte[] byteArray = bas.toByteArray();
        return byteArray;
    }

    /**
     * 生成散点图(返回JFreeChart)
     *
     * @param chartTitle 图表标题
     * @param dataset    数据集
     * @param theme      主题(null代表默认主题)
     * @param yAxisTitle y轴标题
     * @param xAxisTitle x轴标题
     * @return
     */
    public static JFreeChart createScatterPlot(String chartTitle
            , XYDataset dataset, StandardChartTheme theme, String yAxisTitle, String xAxisTitle) throws Exception {
        //设置主题，防止中文乱码
        theme = theme == null ? JFreeChartUtil.createChartTheme("") : theme;
        ChartFactory.setChartTheme(theme);
        //创建散点图
        JFreeChart chart = ChartFactory.createScatterPlot(chartTitle, xAxisTitle, yAxisTitle
                , dataset);
        // 设置抗锯齿，防止字体显示不清楚
        chart.setTextAntiAlias(false);
        //散点图渲染
        JFreeChartUtil.setScatterRender(chart.getXYPlot());
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 标注位于上侧
        chart.getLegend().setPosition(RectangleEdge.TOP);
        return chart;
    }

    /**
     * 生成散点图(返回outputStream)
     *
     * @param chartTitle   图表标题
     * @param dataset      数据集
     * @param theme        主题(null代表默认主题)
     * @param yAxisTitle   y轴标题
     * @param xAxisTitle   x轴标题
     * @param outputStream 输出流
     * @param width        宽度
     * @param height       高度
     * @return
     */
    public static void createScatterPlot(OutputStream outputStream, String chartTitle, XYDataset dataset, StandardChartTheme theme
            , String yAxisTitle, String xAxisTitle, int width, int height
    ) throws Exception {
        JFreeChart chart = createScatterPlot(chartTitle, dataset, theme, yAxisTitle, xAxisTitle);
        try {
            ChartUtils.writeChartAsJPEG(outputStream, 1.0f, chart, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成散点图(返回byte[])
     *
     * @param chartTitle 图表标题
     * @param dataset    数据集
     * @param theme      主题(null代表默认主题)
     * @param yAxisTitle y轴标题
     * @param xAxisTitle x轴标题
     * @param width      宽度
     * @param height     高度
     * @return
     */
    public static byte[] createScatterPlot(String chartTitle, XYDataset dataset, StandardChartTheme theme, String yAxisTitle
            , String xAxisTitle, int width, int height) throws Exception {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        createScatterPlot(bas, chartTitle, dataset, theme, yAxisTitle, xAxisTitle, width, height);
        byte[] byteArray = bas.toByteArray();
        return byteArray;
    }
}
