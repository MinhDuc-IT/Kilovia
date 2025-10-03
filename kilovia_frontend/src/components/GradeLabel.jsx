import React from "react";

const GradeLabel = ({ mainColor = "#4CAF50", triangleColor = "#2E7D32", text = "LỚP 1" }) => {
  return (
    <div className="relative inline-block">
      {/* Khối chính */}
      <div
        className="px-6 py-3 text-white font-bold text-lg rounded-r-full"
        style={{ backgroundColor: mainColor }}
      >
        {text}
      </div>

      {/* Tam giác nhỏ */}
      <div
        className="absolute left-0 top-full w-0 h-0"
        style={{
          borderLeft: "15px solid transparent",
          borderTop: `15px solid ${triangleColor}`,
        }}
      />
    </div>
  );
};

export default GradeLabel;
