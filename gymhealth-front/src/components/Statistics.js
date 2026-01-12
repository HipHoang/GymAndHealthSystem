import React, { useEffect, useState } from "react";
import { Line, Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend
} from "chart.js";
import Apis, { endpoints } from "../configs/Apis";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export default function Statistics() {
  const [loading, setLoading] = useState(true);
  const [weekStats, setWeekStats] = useState([]);
  const [monthStats, setMonthStats] = useState([]);

  useEffect(() => {
    const loadStats = async () => {
      try {
        let weekRes = await Apis.get(endpoints["statsByWeek"]);
        let monthRes = await Apis.get(endpoints["statsByMonth"]);

        console.log("📅 Week Stats:", weekRes.data);
        console.log("📅 Month Stats:", monthRes.data);

        setWeekStats(weekRes.data || []);
        setMonthStats(monthRes.data || []);
      } catch (err) {
        console.error("❌ Lỗi load thống kê:", err);
      } finally {
        setLoading(false);
      }
    };

    loadStats();
  }, []);

  if (loading) return <p>Loading...</p>;

  const weekChartData = {
    labels: weekStats.map((item) => `Week ${item.week}`),
    datasets: [
      {
        label: "Số lượng",
        data: weekStats.map((item) => item.count),
        borderColor: "rgb(75, 192, 192)",
        tension: 0.3
      }
    ]
  };

  const monthChartData = {
    labels: monthStats.map((item) => `Month ${item.month}`),
    datasets: [
      {
        label: "Số lượng",
        data: monthStats.map((item) => item.count),
        backgroundColor: "rgba(54, 162, 235, 0.5)"
      }
    ]
  };

  return (
    <div>
      <h2>📊 Thống kê theo tuần</h2>
      <Line data={weekChartData} />

      <h2 className="mt-5">📊 Thống kê theo tháng</h2>
      <Bar data={monthChartData} />
    </div>
  );
}
